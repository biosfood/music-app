/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.instruments

enum class Waveform(val id: Int, private val identifier: String) {
    SINE(0, "sine"),
    SAWTOOTH(1, "sawtooth"),
    SQUARE(2, "square"),
    TRIANGLE(3, "triangle"),
    ;

    override fun toString(): String {
        return identifier
    }

    companion object {
        val VALUES = values()
        val DEFAULT = SINE
    }
}