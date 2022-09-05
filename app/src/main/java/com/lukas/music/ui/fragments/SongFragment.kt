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
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.lukas.music.databinding.FragmentSongBinding
import com.lukas.music.song.Song
import com.lukas.music.song.chords.Phrase
import com.lukas.music.song.note.Note
import com.lukas.music.song.note.NoteName
import com.lukas.music.util.UIUtil


class SongFragment(val playFragment: PlayFragment) : Fragment(),
    AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSongBinding
    var displayChordNames = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater)
        binding.chords.isStretchAllColumns = true
        binding.literalChords.isChecked = true
        binding.literalChords.setOnCheckedChangeListener { _, isChecked ->
            displayChordNames = isChecked
            updateChords()
        }
        val adapter = ArrayAdapter(
            binding.root.context,
            android.R.layout.simple_spinner_dropdown_item, NoteName.VALUES
        )
        binding.keySelection.adapter = adapter
        binding.keySelection.onItemSelectedListener = this
        binding.keySelection.setSelection(Song.currentSong.root.noteName.index)
        binding.addPhraseButton.setOnClickListener {
            Song.currentSong.chordProgression += Phrase()
            updateChords()
        }
        updateChords()
        return binding.root
    }

    fun updateChords() {
        binding.chords.removeAllViews()
        for (phrase in Song.currentSong.chordProgression) {
            val row = TableRow(binding.root.context)
            for (chord in phrase) {
                val card = CardView(binding.root.context)
                card.radius = 10f
                card.layoutParams = UIUtil.cardLayout
                card.setOnClickListener {
                    EditChordFragment(chord, this).showNow(childFragmentManager, "")
                }
                val text = TextView(binding.root.context)
                text.text = chord.toString(displayChordNames, Song.currentSong.root)
                text.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                text.layoutParams = UIUtil.fillingLayout
                text.textSize = 20f
                card.addView(text)
                row.addView(card)
            }
            val button = ImageButton(binding.root.context)
            button.setOnClickListener {
                Song.currentSong.chordProgression -= phrase
                updateChords()
            }
            button.setImageResource(android.R.drawable.ic_delete)
            button.layoutParams = UIUtil.buttonLayout
            row.addView(button)
            binding.chords.addView(row)
        }
        playFragment.updateChords()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Song.currentSong.root = Note.of(NoteName.VALUES[position], 4)
        if (displayChordNames) {
            updateChords()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}