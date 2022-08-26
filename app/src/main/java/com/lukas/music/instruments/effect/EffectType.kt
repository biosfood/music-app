/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.instruments.effect

import com.lukas.music.util.format

enum class EffectType(
    val title: String,
    val parameterDescriptions: Array<EffectParameterDescription>
) {
    LowPass("low pass filter",
        arrayOf(
            EffectParameterDescription(-1f, 3f, 1f) {
                "cutoff: ${it.value.format(1)} octaves"
            }
        )),
    Noise("noise",
        arrayOf(
            EffectParameterDescription(0f, 1f, 0f) {
                "unused"
            }
        )
    )
    ;

    override fun toString(): String {
        return title
    }

    companion object {
        val VALUES = values()
    }
}