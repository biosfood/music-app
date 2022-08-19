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

import com.lukas.music.databinding.FragmentInstrumentBinding
import com.lukas.music.song.note.Note
import com.lukas.music.song.voice.BassVoice
import com.lukas.music.song.voice.ChordVoice
import com.lukas.music.song.voice.Voice

abstract class Instrument(private var name: String) {
    private var active = false
    abstract var waveform: Waveform

    fun applyToView(binding: FragmentInstrumentBinding) {
        binding.instrumentNameText.text = name
        binding.editInstrumentButton.setOnClickListener {
            println("click instrument $name")
        }
        binding.activeSwitch.setOnCheckedChangeListener { _, newActive ->
            active = newActive
            changeActive(newActive)
        }
        binding.activeSwitch.isChecked = active
    }

    abstract fun startNote(note: Note)
    abstract fun stop()
    abstract fun changeActive(newActive: Boolean)

    companion object {
        val instruments =
            mutableListOf<Instrument>(
                MonoInstrument("Bass"),
                PolyInstrument("Chords"),
            )

        val voice = mutableListOf<Voice>(
            BassVoice(instruments[0]),
            ChordVoice(instruments[1]),
        )
    }
}