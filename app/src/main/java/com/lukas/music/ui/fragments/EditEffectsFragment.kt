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
import com.lukas.music.databinding.FragmentEditEffectsBinding
import com.lukas.music.instruments.Instrument
import com.lukas.music.util.EasyDialogFragment

class EditEffectsFragment(private val instrument: Instrument) :
    EasyDialogFragment<FragmentEditEffectsBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditEffectsBinding.inflate(inflater)
        for (effect in instrument.effects) {
            val effectEditor = EffectFragment(effect)
            childFragmentManager.beginTransaction().add(binding.effectsDisplay.id, effectEditor)
                .commit()
        }
        binding.closeButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }
}