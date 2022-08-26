/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.song

import com.lukas.music.song.chords.ChordType

enum class ScaleType(
    val identifier: String,
    val steps: Array<Int>,
    val chordTypes: Array<ChordType>
) {
    MAJOR(
        "major",
        arrayOf(0, 2, 4, 5, 7, 9, 11, 12),
        arrayOf(
            ChordType.MAJOR,
            ChordType.MINOR,
            ChordType.MINOR,
            ChordType.MAJOR,
            ChordType.MAJOR,
            ChordType.MINOR,
            ChordType.DIMINISHED
        )
    )
}