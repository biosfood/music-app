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

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.lukas.music.R
import com.lukas.music.databinding.FragmentInstrumentBinding
import com.lukas.music.instruments.Instrument
import com.lukas.music.song.Song
import com.lukas.music.ui.fragments.EditInstrumentFragment
import com.lukas.music.util.setupToggle
import com.lukas.music.util.updateToggle

class InstrumentViewHolder(
    val binding: FragmentInstrumentBinding,
    private val childFragmentManager: FragmentManager,
    private val adapter: InstrumentAdapter
) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        HOLDERS += this
    }

    var solo: Boolean = false
        set(value) {
            if (value) {
                for (holder in HOLDERS) {
                    holder.solo = false
                }
                Song.currentSong.soloInstrument = instrument
            }
            field = value
            binding.soloButton.updateToggle(this::solo, R.color.blue)
        }

    var instrument: Instrument? = null
        set(value) {
            field = value
            value ?: return
            binding.instrumentNameText.text = instrument?.name
            binding.editInstrumentButton.setOnClickListener {
                EditInstrumentFragment(instrument!!, this).showNow(childFragmentManager, "")
            }
            binding.muteButton.setupToggle(instrument!!::muted, R.color.red)
            binding.soloButton.setupToggle(this::solo, R.color.blue) {
                if (!it) {
                    Song.currentSong.soloInstrument = null
                }
            }
            binding.deleteButton.setOnClickListener {
                val index = Instrument.instruments.indexOf(instrument)
                Instrument.instruments -= instrument!!
                adapter.notifyItemRemoved(index)
                instrument!!.destroy()
            }
        }

    companion object {
        val HOLDERS = mutableListOf<InstrumentViewHolder>()
    }
}