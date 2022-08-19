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

import com.lukas.music.song.note.Note

class PolyInstrument(name: String) : Instrument(name) {
    private val internalInstruments = Array(3) { InternalInstrument() }
    private val playing = Array(3) { false }

    override var waveform: Waveform = Waveform.SINE
        set(value) {
            field = value
            for (internalInstrument in internalInstruments) {
                internalInstrument.waveform = value
            }
        }

    override var volume: Float = 1.0f
        set(value) {
            field = value
            for (internalInstrument in internalInstruments) {
                internalInstrument.volume = volume
            }
        }

    override var muted: Boolean = false
        set(value) {
            field = value
            for (instrument in internalInstruments) {
                instrument.muted = value
            }
        }

    override fun startNote(note: Note) {
        for ((index, instrumentPlaying) in playing.withIndex()) {
            if (!instrumentPlaying) {
                internalInstruments[index].startNote(note.frequency)
                playing[index] = true
                return
            }
        }
        throw IllegalStateException("cannot start another note with the current amount of oscillators")
    }

    override fun stop() {
        for ((i, instrument) in internalInstruments.withIndex()) {
            instrument.endNote()
            playing[i] = false
        }
    }
}