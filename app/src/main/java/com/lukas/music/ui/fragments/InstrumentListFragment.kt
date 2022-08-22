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

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lukas.music.databinding.FragmentInstrumentListBinding
import com.lukas.music.instruments.Instrument
import com.lukas.music.instruments.MonoInstrument
import com.lukas.music.instruments.PolyInstrument
import com.lukas.music.ui.adapters.InstrumentAdapter

class InstrumentListFragment : Fragment() {
    lateinit var binding: FragmentInstrumentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInstrumentListBinding.inflate(inflater)
        binding.recyclerView.adapter = InstrumentAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        val callback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
            0
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val adapter = recyclerView.adapter as InstrumentAdapter
                val startPosition = viewHolder.adapterPosition
                val endPosition = target.adapterPosition
                val instrument = Instrument.instruments[startPosition]
                Instrument.instruments.removeAt(startPosition)
                if (endPosition < startPosition) {
                    Instrument.instruments.add(endPosition + 1, instrument)
                } else {
                    Instrument.instruments.add(endPosition - 1, instrument)
                }
                adapter.notifyItemMoved(startPosition, endPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
        }
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(binding.recyclerView)

        val builder = AlertDialog.Builder(binding.root.context)
        builder.setTitle("Instrument type:")
            .setItems(
                arrayOf("mono", "poly")
            ) { _, index ->
                when (index) {
                    0 -> Instrument.instruments += MonoInstrument("New mono Instrument")
                    1 -> Instrument.instruments += PolyInstrument("New poly Instrument")
                }
                (binding.recyclerView.adapter as RecyclerView.Adapter).notifyItemInserted(
                    Instrument.instruments.size - 1
                )
            }
        builder.create()
        binding.addInstrumentButton.setOnClickListener {
            builder.show()
        }
        return binding.root
    }
}