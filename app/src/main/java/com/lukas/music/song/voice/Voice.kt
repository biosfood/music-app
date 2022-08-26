/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.song.voice

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.Song
import com.lukas.music.song.note.Note

class Voice(val instrument: Instrument) {
    var type: VoiceType = VoiceType.Bass
        set(value) {
            field = value
            noteActive =
                Array(Song.currentSong.beats * Song.currentSong.subBeats) { Array(value.noteCount) { false } }
        }
    var restrikeNotes = false
    lateinit var noteActive: Array<Array<Boolean>>

    var octaveOffset = 0

    init {
        type = type
    }

    fun step(root: Note, chordNotes: Array<Note>, beat: Int, subBeat: Int) {
        if (instrument.muted) {
            return
        }
        val beatIndex = beat * Song.currentSong.subBeats + subBeat
        val activeNotes = noteActive[beatIndex]
        val notes = type.getNotes(root, chordNotes)
        for ((index, active) in activeNotes.withIndex()) {
            val note = notes[index] + 12 * octaveOffset
            if (!active) {
                instrument.stopNote(note)
                continue
            }
            if (restrikeNotes || !instrument.isPlaying(note)) {
                instrument.startNote(note)
            }
        }
    }
}