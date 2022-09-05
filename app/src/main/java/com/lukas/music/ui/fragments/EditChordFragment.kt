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
import android.widget.TextView
import androidx.core.view.children
import androidx.core.view.setMargins
import androidx.fragment.app.DialogFragment
import com.google.android.material.button.MaterialButton
import com.lukas.music.R
import com.lukas.music.databinding.FragmentEditChordBinding
import com.lukas.music.song.ScaleType
import com.lukas.music.song.Song
import com.lukas.music.song.chords.Accidental
import com.lukas.music.song.chords.Chord
import com.lukas.music.song.chords.Interval
import com.lukas.music.util.setup
import com.lukas.music.util.updateToggle

class EditChordFragment(private val chord: Chord, private val songFragment: SongFragment) :
    DialogFragment() {
    lateinit var binding: FragmentEditChordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditChordBinding.inflate(inflater)
        setupPitchSpinner()
        setupEditor()
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    private fun update() {
        songFragment.updateChords()
        binding.chordText.text = chord.toString(true, Song.currentSong.root)
        updateEditor()
    }

    private fun setupPitchSpinner() {
        val pitches = if (songFragment.displayChordNames) {
            Array(ScaleType.MAJOR.steps.size) { (Song.currentSong.root + ScaleType.MAJOR.steps[it]).noteName.toString() }
        } else Interval.IntervalName.NAMES
        binding.pitchSpinner.setup(pitches, chord.interval.name.ordinal) {
            chord.note = ScaleType.MAJOR.steps[it]
            update()
            // todo: setup chord to be the correct type
        }
    }

    private fun setupEditor() {
        binding.editorGrid.removeAllViews()
        val row = TableRow(binding.root.context)
        for (description in descriptions) {
            val text = TextView(binding.root.context)
            text.text = description
            text.layoutParams = layout
            text.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            row.addView(text)
        }
        binding.editorGrid.addView(row)
        for (accidental in Accidental.VALUES) {
            val row = TableRow(binding.root.context)
            for (position in 0 until Chord.NOTE_COUNT - 1) {
                val button = MaterialButton(binding.root.context)
                button.text = accidental.toString()
                button.layoutParams = layout
                button.updateToggle(chord.accidentals[position] == accidental, R.color.blue)
                button.setOnClickListener {
                    if (chord.accidentals[position] == accidental) {
                        chord.accidentals[position] = null
                    } else {
                        chord.accidentals[position] = accidental
                    }
                    update()
                }
                row.addView(button)
            }
            binding.editorGrid.addView(row)
        }
    }

    private fun updateEditor() {
        for ((index, view) in binding.editorGrid.children.iterator().withIndex()) {
            if (index == 0) {
                continue
            }
            view as TableRow
            for ((childIndex, childView) in view.children.iterator().withIndex()) {
                childView as MaterialButton
                childView.updateToggle(
                    chord.accidentals[childIndex] == Accidental.VALUES[index - 1],
                    R.color.blue
                )
            }
        }
    }

    companion object {
        val layout = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        init {
            layout.weight = 1.0f
            layout.setMargins(5)
        }

        val descriptions = arrayOf("III", "V", "VII", "IX")
    }
}