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
import android.widget.TableRow
import androidx.core.view.setMargins
import com.google.android.material.button.MaterialButton
import com.lukas.music.R
import com.lukas.music.databinding.FragmentEditVoiceBinding
import com.lukas.music.song.Song
import com.lukas.music.song.voice.Voice
import com.lukas.music.util.ArrayProperty
import com.lukas.music.util.EasyDialogFragment
import com.lukas.music.util.setupToggle

class EditVoiceFragment(private val voice: Voice) : EasyDialogFragment<FragmentEditVoiceBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditVoiceBinding.inflate(inflater)
        binding.restrikeButton.setupToggle(voice::restrikeNotes, R.color.green)
        for (row in voice.type.noteCount - 1 downTo 0) {
            val rowLayout = TableRow(binding.root.context)
            for (column in 0 until Song.currentSong.beats * Song.currentSong.subBeats) {
                val button = MaterialButton(binding.root.context)
                button.layoutParams = buttonLayout
                button.setupToggle(
                    ArrayProperty(voice.noteActive[column], row),
                    R.color.blue,
                    inactiveColor = if (column % Song.currentSong.subBeats == 0) R.color.gray_0x50 else R.color.gray_0x70
                )
                rowLayout.addView(button)
            }
            binding.noteGrid.addView(rowLayout)
        }
        binding.noteGrid.isStretchAllColumns = true
        binding.closeButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    companion object {
        val buttonLayout = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT)

        init {
            buttonLayout.setMargins(5)
        }
    }
}