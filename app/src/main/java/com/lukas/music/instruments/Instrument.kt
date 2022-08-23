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
import com.lukas.music.song.voice.BassVoice
import com.lukas.music.song.voice.Voice

abstract class Instrument(var name: String) {
    var voice: Voice = BassVoice(this)
    abstract var waveform: Waveform
    abstract var volume: Float
    abstract var muted: Boolean

    abstract fun startNote(note: Note)
    abstract fun stop()
    abstract fun stopNote(note: Note)
    abstract fun destroy()

    companion object {
        val instruments = mutableListOf<Instrument>()
    }
}