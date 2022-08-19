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

enum class ChordType(
    val notes: Array<Int>,
    private val asString: String,
    val transform: (String) -> String
) {
    MAJOR(arrayOf(0, 4, 7), "major", { it.uppercase() }),
    MINOR(arrayOf(0, 3, 7), "minor", { it.lowercase() }),
    DIMINISHED(arrayOf(0, 3, 6), "diminished", { it.lowercase() + "0" }),
    ;

    override fun toString(): String {
        return asString
    }

    companion object {
        val VALUES = values()
    }
}