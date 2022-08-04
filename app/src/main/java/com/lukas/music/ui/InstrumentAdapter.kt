package com.lukas.music.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lukas.music.databinding.FragmentInstrumentBinding
import com.lukas.music.instruments.Instrument


class InstrumentAdapter : RecyclerView.Adapter<InstrumentAdapter.InstrumentViewHolder>() {
    class InstrumentViewHolder(val binding: FragmentInstrumentBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val instruments =
        mutableListOf<Instrument>(Instrument("First Instrument"), Instrument("second instrument"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstrumentViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = FragmentInstrumentBinding.inflate(inflater, parent, false)
        return InstrumentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstrumentViewHolder, position: Int) {
        val instrument = instruments[position]
        holder.binding.instrumentNameText.text = instrument.name
    }

    override fun getItemCount(): Int {
        return instruments.size
    }
}