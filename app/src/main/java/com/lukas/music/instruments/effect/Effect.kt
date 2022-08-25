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

class Effect(val type: EffectType, private val instrument: Instrument) {
    var parameter1: Float = 1.0f
        set(value) {
            field = value
            instrument.updateEffects()
        }
    var parameter1Percent: Int
        get() = (parameter1 * 100).toInt()
        set(value) {
            parameter1 = value.toFloat() / 100f
        }
}