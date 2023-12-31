/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lukas.music.databinding.FragmentEditInstrumentBinding
import com.lukas.music.instruments.Instrument
import com.lukas.music.instruments.Waveform
import com.lukas.music.song.voice.VoiceType
import com.lukas.music.ui.adapters.InstrumentViewHolder
import com.lukas.music.util.EasyDialogFragment
import com.lukas.music.util.setup
import com.lukas.music.util.smartSetup

class EditInstrumentFragment(
    private val instrument: Instrument,
    private val viewHolder: InstrumentViewHolder
) : EasyDialogFragment<FragmentEditInstrumentBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditInstrumentBinding.inflate(inflater)
        binding.instrumentNameTextBox.text.clear()
        binding.instrumentNameTextBox.text.append(instrument.name)
        binding.instrumentNameTextBox.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                instrument.name = binding.instrumentNameTextBox.text.toString()
                viewHolder.instrument = viewHolder.instrument
            }
        })
        binding.waveformSelection.smartSetup(Waveform.VALUES, instrument::waveform)
        binding.volumeSeek.setup(0, 100, (instrument.volume * 100f).toInt()) {
            binding.volumeText.text = "volume: $it%"
            instrument.volume = it.toFloat() / 100f
        }
        binding.voiceSelection.smartSetup(VoiceType.VALUES, instrument.voice::type)
        binding.editVoiceButton.setOnClickListener {
            EditVoiceFragment(instrument.voice).showNow(childFragmentManager, "")
        }
        binding.editEnvelopeButton.setOnClickListener {
            EditEnvelopeFragment(instrument.envelope).showNow(childFragmentManager, "")
        }
        binding.editEffectsButton.setOnClickListener {
            EditEffectsFragment(instrument).showNow(childFragmentManager, "")
        }
        binding.closeButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}