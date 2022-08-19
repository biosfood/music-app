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

import android.R
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.lukas.music.databinding.FragmentEditInstrumentBinding
import com.lukas.music.databinding.FragmentInstrumentBinding
import com.lukas.music.instruments.Instrument
import com.lukas.music.instruments.Waveform

class EditInstrumentFragment(
    private val instrument: Instrument,
    private val parent: FragmentInstrumentBinding,
    private val manager: FragmentManager
) : DialogFragment() {
    lateinit var binding: FragmentEditInstrumentBinding

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
                instrument.applyToView(parent, manager)
            }
        })
        val adapter = ArrayAdapter(
            binding.root.context,
            R.layout.simple_spinner_dropdown_item, Waveform.VALUES
        )
        binding.waveformSelection.adapter = adapter
        binding.waveformSelection.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    instrument.waveform = Waveform.VALUES[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
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