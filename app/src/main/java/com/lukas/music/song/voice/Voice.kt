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
import com.lukas.music.song.note.Note
import kotlin.reflect.KClass

abstract class Voice(val instrument: Instrument) {
    abstract var noteActive: Array<Array<Boolean>>

    abstract fun getNotes(root: Note, chordNotes: Array<Note>): Array<Note>

    fun step(root: Note, chordNotes: Array<Note>, beat: Int) {
        if (instrument.muted) {
            return
        }
        val activeNotes = noteActive[beat]
        val notes = getNotes(root, chordNotes)
        for ((index, active) in activeNotes.withIndex()) {
            val note = notes[index]
            if (!active) {
                instrument.stopNote(note)
                continue
            }
            instrument.startNote(note)
        }
    }

    companion object {
        val DEFAULT_VOICES = listOf<KClass<out Voice>>(
            BassVoice::class,
            ChordVoice::class,
        )

        val DEFAULT_VOICE_NAMES = listOf("Bass", "Chord")
    }
}