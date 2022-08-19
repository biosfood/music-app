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

class InternalInstrument {
    private val id = createInstrument()
    var active: Boolean = false
        set(value) {
            field = value
            setInstrumentActive(id, value)
        }

    var waveform: Waveform = Waveform.SINE
        set(value) {
            field = value
            setInstrumentWaveform(id, value.id)
            // this is to resend the setInstrumentActive for the new waveform in the internal c++ code
            active = active
        }

    fun startNote(frequency: Double) {
        startNote(id, frequency)
    }

    fun endNote() {
        endNote(id)
    }

    private external fun createInstrument(): Int
    private external fun setInstrumentActive(id: Int, isActive: Boolean)
    private external fun setInstrumentWaveform(id: Int, waveform: Int)
    private external fun startNote(id: Int, frequency: Double)
    private external fun endNote(id: Int)
}