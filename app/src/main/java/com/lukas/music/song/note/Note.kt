/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.song.note

import kotlin.math.pow

class Note(val id: Int) {
    val noteName = NoteName.VALUES[id % 12]
    val octave = id / 12 - 1
    val frequency = 440 * 2.0.pow((id - 69) / 12.0)

    operator fun plus(other: Int): Note {
        if (id + other < 0 || id + other > 127) {
            throw IllegalArgumentException("cannot add $other to note with id $id")
        }
        return NOTES[id + other]
    }

    operator fun minus(other: Int): Note {
        return this + (-other)
    }

    operator fun minus(other: Note): Int = id - other.id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Note

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }


    companion object {
        val NOTES = Array(128) { Note(it) }

        fun of(noteName: NoteName, octave: Int): Note {
            return NOTES[12 * (octave + 1) + noteName.index]
        }
    }
}