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

import com.lukas.music.instruments.Instrument
import kotlin.math.roundToInt

class EffectParameter(val description: EffectParameterDescription, val instrument: Instrument) {
    var value: Float = description.initialValue
        set(value) {
            field = value
            instrument.updateEffects()
        }

    // linear interpolation between description extrema
    var percentageValue: Int
        get() = ((value - description.min) / (description.max - description.min) * 100).roundToInt()
        set(value) {
            this.value =
                description.min + (description.max - description.min) * (value.toFloat() / 100f)
        }
}