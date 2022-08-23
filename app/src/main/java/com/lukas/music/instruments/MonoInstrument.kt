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

class MonoInstrument(name: String) : Instrument(name) {
    private val internalInstrument = InternalInstrument()

    override var waveform: Waveform = Waveform.SINE
        set(value) {
            field = value
            internalInstrument.waveform = value
        }

    override var volume: Float = 1.0f
        set(value) {
            field = value
            internalInstrument.volume = volume
        }

    override var muted: Boolean = false
        set(value) {
            field = value
            internalInstrument.muted = value
        }

    override fun startNote(note: Note) {
        if (note == internalInstrument.note) {
            return
        }
        internalInstrument.startNote(note)
    }

    override fun stop() {
        internalInstrument.endNote()
    }

    override fun stopNote(note: Note) {
        if (note == internalInstrument.note) {
            stop()
        }
    }

    override fun destroy() {
        internalInstrument.destroy()
    }
}