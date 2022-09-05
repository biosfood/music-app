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

enum class Accidental(val id: String, val short: String, val distance: Int) {
    Flat("\u266D", "b", -1),
    None("\u266E", "", 0),
    Sharp("\u266F", "#", 1),
    ;

    override fun toString(): String {
        return id
    }

    companion object {
        val VALUES = values()
    }
}