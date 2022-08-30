/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukas.music.databinding.FragmentEffectBinding
import com.lukas.music.instruments.Instrument
import com.lukas.music.ui.fragments.EditEffectsFragment
import com.lukas.music.ui.fragments.EffectFragment

class EffectsAdapter(private val parent: EditEffectsFragment, private val instrument: Instrument) :
    RecyclerView.Adapter<EffectFragment>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EffectFragment {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = FragmentEffectBinding.inflate(inflater, parent, false)
        return EffectFragment(binding)
    }

    override fun onBindViewHolder(holder: EffectFragment, position: Int) {
        holder.setEffect(instrument.effects[position])
    }

    override fun getItemCount(): Int {
        return instrument.effects.size
    }
}