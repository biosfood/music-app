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
import androidx.fragment.app.Fragment
import com.lukas.music.R
import com.lukas.music.databinding.FragmentEffectBinding
import com.lukas.music.instruments.effect.Effect
import com.lukas.music.util.setupToggle
import com.lukas.music.util.smartSetup

class EffectFragment(private val effect: Effect) : Fragment() {
    lateinit var binding: FragmentEffectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEffectBinding.inflate(inflater)
        binding.effectName.text = effect.type.toString()
        binding.activeButton.setupToggle(effect::active, R.color.blue) {
            binding.activeButton.text = if (it) "ON" else "OFF"
        }
        binding.activeButton.text = if (effect.active) "ON" else "OFF"
        binding.influenceSeekBar.smartSetup(0, 100, effect.influence::percentageValue) {
            binding.influenceText.text = effect.influence.description.text(effect.influence)
        }
        binding.parameter1SeekBar.smartSetup(0, 100, effect.parameters[0]::percentageValue) {
            binding.parameter1Text.text =
                effect.parameters[0].description.text(effect.parameters[0])
        }
        return binding.root
    }
}