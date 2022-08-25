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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.lukas.music.databinding.FragmentEditEnvelopeBinding
import com.lukas.music.instruments.Envelope
import com.lukas.music.util.smartSetup

class EditEnvelopeFragment(private val envelope: Envelope) : DialogFragment() {
    lateinit var binding: FragmentEditEnvelopeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditEnvelopeBinding.inflate(inflater)
        binding.attackSeek.smartSetup(5, 200, envelope::attack) {
            binding.attackText.text = "Attack: $it ms"
        }
        binding.delaySeek.smartSetup(5, 200, envelope::delay) {
            binding.delayText.text = "Delay: $it ms"
        }
        binding.sustainSeek.smartSetup(0, 100, envelope::sustain) {
            binding.sustainText.text = "Sustain: $it%"
        }
        binding.releaseSeek.smartSetup(5, 200, envelope::release) {
            binding.releaseText.text = "Release: $it ms"
        }
        binding.closeButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
}