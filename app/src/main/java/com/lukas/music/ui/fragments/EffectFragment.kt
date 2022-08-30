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

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.lukas.music.R
import com.lukas.music.databinding.FragmentEffectBinding
import com.lukas.music.instruments.effect.Effect
import com.lukas.music.util.setupToggle
import com.lukas.music.util.smartSetup

class EffectFragment(val binding: FragmentEffectBinding) : RecyclerView.ViewHolder(
    binding.root
) {
    fun setEffect(effect: Effect) {
        binding.effectName.text = effect.type.toString()
        binding.activeButton.setupToggle(effect::active, R.color.blue) {
            binding.activeButton.text = if (it) "ON" else "OFF"
        }
        binding.activeButton.text = if (effect.active) "ON" else "OFF"
        binding.influenceSeekBar.smartSetup(0, 100, effect.influence::percentageValue) {
            binding.influenceText.text = effect.influence.description.text(effect.influence)
        }
        binding.parameter1SeekBar.visibility =
            if (effect.parameters[0] == null) View.GONE else View.VISIBLE
        binding.parameter1Text.visibility =
            if (effect.parameters[0] == null) View.GONE else View.VISIBLE
        effect.parameters[0]?.let {
            binding.parameter1SeekBar.smartSetup(0, 100, it::percentageValue) {
                binding.parameter1Text.text =
                    effect.parameters[0]!!.description.text(effect.parameters[0]!!)
            }
        }
    }
}