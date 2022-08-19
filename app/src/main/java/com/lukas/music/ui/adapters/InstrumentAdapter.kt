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
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lukas.music.databinding.FragmentInstrumentBinding
import com.lukas.music.instruments.Instrument
import com.lukas.music.instruments.Waveform


class InstrumentAdapter : RecyclerView.Adapter<InstrumentAdapter.InstrumentViewHolder>() {
    class InstrumentViewHolder(val binding: FragmentInstrumentBinding) :
        RecyclerView.ViewHolder(binding.root), AdapterView.OnItemSelectedListener {
        lateinit var instrument: Instrument

        init {
            val adapter = ArrayAdapter(
                binding.root.context,
                android.R.layout.simple_spinner_dropdown_item, Waveform.VALUES
            )
            binding.waveformSelection.adapter = adapter
            binding.waveformSelection.onItemSelectedListener = this
        }

        override fun onItemSelected(
            adapterView: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            instrument.waveform = Waveform.VALUES[position]
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = FragmentInstrumentBinding.inflate(inflater, parent, false)
        return InstrumentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstrumentViewHolder, position: Int) {
        val instrument = Instrument.instruments[position]
        holder.instrument = instrument
        instrument.applyToView(holder.binding)
    }

    override fun getItemCount(): Int {
        return Instrument.instruments.size
    }
}