package com.lukas.music.ui

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