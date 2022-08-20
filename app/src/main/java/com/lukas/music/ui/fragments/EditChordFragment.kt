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
import androidx.fragment.app.DialogFragment
import com.lukas.music.databinding.FragmentEditChordBinding
import com.lukas.music.song.Scale
import com.lukas.music.song.Song
import com.lukas.music.song.chords.Chord
import com.lukas.music.song.chords.ChordType
import com.lukas.music.song.chords.Interval
import com.lukas.music.util.setup

class EditChordFragment(val chord: Chord, private val songFragment: SongFragment) :
    DialogFragment() {
    lateinit var binding: FragmentEditChordBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditChordBinding.inflate(inflater)
        setupPitchSpinner()
        setupTypeSpinner()
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        return binding.root
    }

    private fun setupPitchSpinner() {
        val pitches = if (songFragment.displayChordNames) {
            Array(Scale.MAJOR.steps.size) { (Song.currentSong.root + Scale.MAJOR.steps[it]).noteName.toString() }
        } else Interval.IntervalName.NAMES
        binding.pitchSpinner.setup(pitches, chord.interval.name.ordinal) {
            chord.note = Scale.MAJOR.steps[it]
            if (binding.typeSpinner.selectedItemPosition == 0) {
                chord.chordType = Scale.MAJOR.chordTypes[chord.interval.name.ordinal]
            }
            songFragment.updateChords()
        }
    }

    private fun setupTypeSpinner() {
        val values = mutableListOf("default")
        for (chordType in ChordType.VALUES) {
            values += chordType.toString()
        }
        binding.typeSpinner.setup(
            values,
            if (chord.chordType == Scale.MAJOR.chordTypes[chord.interval.name.ordinal]) 0
            else chord.chordType.ordinal + 1
        ) {
            if (it == 0) {
                chord.chordType = Scale.MAJOR.chordTypes[chord.interval.name.ordinal]
            } else {
                chord.chordType = ChordType.VALUES[it - 1]
            }
            songFragment.updateChords()
        }
    }
}