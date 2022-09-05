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

import com.lukas.music.song.ScaleType
import com.lukas.music.song.chords.Chord
import com.lukas.music.song.note.Note
import com.lukas.music.util.transform

enum class VoiceType(
    val title: String,
    val noteCount: Int,
    val getNotes: (Note, Array<Note>) -> Array<Note>
) {
    Bass("Bass note", 1, { _, chordNotes -> arrayOf(chordNotes[0]) }),
    ChordVoice("Chord notes", Chord.NOTE_COUNT, { _, chordNotes -> chordNotes }),
    Scale("Scale notes", 8, { root, _ -> ScaleType.MAJOR.steps.transform { root + it } }),
    Root("Root note", 1, { root, _ -> arrayOf(root) }),
    RootRelative("Song root relative", 12, { root, _ -> Array(12) { root + it } }),
    ;

    override fun toString(): String {
        return title
    }

    companion object {
        val VALUES = values()
    }
}