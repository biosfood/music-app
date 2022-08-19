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

class Interval(val distance: Int) {
    val name: IntervalName = when (distance) {
        0 -> IntervalName.UNISON
        1, 2 -> IntervalName.SECOND
        3, 4 -> IntervalName.THIRD
        5, 6 -> IntervalName.FOURTH
        7 -> IntervalName.FIFTH
        8, 9 -> IntervalName.SIXTH
        10, 11 -> IntervalName.SEVENTH
        12 -> IntervalName.OCTAVE
        else -> throw IllegalArgumentException("cannot make interval from distance $distance")
    }
    private val modifier: Modifier = when (distance) {
        0, 7, 12 -> Modifier.PERFECT
        1, 3, 5, 8, 10 -> Modifier.MINOR
        else -> Modifier.MAJOR
    }

    override fun toString(): String {
        return name.toString()
    }

    enum class IntervalName(private val distance: Int, val romanVersion: String) {
        UNISON(0, "I"),
        SECOND(1, "II"),
        THIRD(3, "III"),
        FOURTH(5, "IV"),
        FIFTH(7, "V"),
        SIXTH(8, "VI"),
        SEVENTH(10, "VII"),
        OCTAVE(12, "VIII")
        ;

        override fun toString(): String {
            return romanVersion
        }

        companion object {
            val VALUES = values()
            val NAMES = Array(VALUES.size) { VALUES[it].romanVersion }
        }
    }

    enum class Modifier(val descriptor: String, val offset: Int) {
        PERFECT("", 0),
        MINOR("", 0),
        MAJOR("", 1);

        companion object {
            val VALUES = values()
        }
    }
}