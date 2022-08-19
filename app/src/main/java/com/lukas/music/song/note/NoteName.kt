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

const val A4 = 440.0

enum class NoteName(val index: Int, val asString: String) {
    C(0, "C"),
    Csharp(1, "C#"),
    D(2, "D"),
    Dsharp(3, "D#"),
    E(4, "E"),
    F(5, "F"),
    Fsharp(6, "F#"),
    G(7, "G"),
    Gsharp(8, "G#"),
    A(9, "A"),
    Asharp(10, "A#"),
    B(11, "B"),
    ;

    val baseFrequency: Double = A4 * 2.0.pow(index.toDouble() / 12)

    operator fun plus(interval: Int): NoteName {
        val resultPosition = (index + interval) % VALUES.size
        return VALUES[resultPosition]
    }

    override fun toString(): String {
        return asString
    }

    companion object {
        val VALUES = values()
    }
}