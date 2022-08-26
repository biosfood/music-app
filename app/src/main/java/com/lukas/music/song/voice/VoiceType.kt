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

import com.lukas.music.song.note.Note

enum class VoiceType(
    val title: String,
    val noteCount: Int,
    val getNotes: (Note, Array<Note>) -> Array<Note>
) {
    Bass("Bass Note", 1, { _, chordNotes -> arrayOf(chordNotes[0]) }),
    Chord("Chord Notes", 3, { _, chordNotes -> chordNotes }),
    RootRelative("Song root relative", 12, { root, _ -> Array(12) { root + it } }),
    ;

    override fun toString(): String {
        return title
    }

    companion object {
        val VALUES = values()
    }
}