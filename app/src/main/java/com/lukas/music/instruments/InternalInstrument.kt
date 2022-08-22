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

class InternalInstrument {
    private val id = createInstrument()
    var note: Note? = null

    var waveform: Waveform = Waveform.SINE
        set(value) {
            field = value
            setInstrumentWaveform(id, value.id)
            refresh()
        }

    var volume: Float = 0.3f
        set(value) {
            field = value
            if (!muted) {
                actualVolume = value
            }
        }

    var muted: Boolean = false
        set(value) {
            field = value
            actualVolume = if (value) 0.0f else volume
        }

    private var actualVolume: Float = 1.0f
        set(value) {
            field = value
            setVolume(id, value)
        }

    init {
        refresh()
    }

    private fun refresh() {
        // this is to resend the old information to the internal c++ code (when changing the waveform)
        muted = muted
        volume = volume
    }

    fun startNote(note: Note) {
        this.note = note
        startNote(id, note.frequency)
    }

    fun endNote() {
        note = null
        endNote(id)
    }

    private external fun createInstrument(): Int
    private external fun setInstrumentWaveform(id: Int, waveform: Int)
    private external fun startNote(id: Int, frequency: Double)
    private external fun endNote(id: Int)
    private external fun setVolume(id: Int, volume: Float)
}