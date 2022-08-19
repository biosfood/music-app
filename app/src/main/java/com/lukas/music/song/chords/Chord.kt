/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.song.chords

import com.lukas.music.song.note.Note

class Chord(note: Int, var chordType: ChordType) {
    var note: Int = note
        set(value) {
            field = value
            interval = Interval(value)
        }
    var interval = Interval(note)
        set(value) {
            field = value
            note = value.distance
        }

    fun getNotes(root: Note): Array<Note> {
        return Array(chordType.notes.size) { root + note + chordType.notes[it] }
    }

    override fun toString(): String {
        return chordType.transform(interval.toString())
    }

    fun toString(displayChordNames: Boolean, root: Note): String {
        val base = if (displayChordNames) {
            (root + note).noteName.toString()
        } else {
            interval.toString()
        }
        return chordType.transform(base)
    }
}