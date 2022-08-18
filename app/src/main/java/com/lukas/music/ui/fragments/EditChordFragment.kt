package com.lukas.music.ui.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.lukas.music.databinding.FragmentEditChordBinding
import com.lukas.music.song.Scale
import com.lukas.music.song.Song
import com.lukas.music.song.chords.Chord
import com.lukas.music.song.chords.ChordType
import com.lukas.music.song.chords.Interval

class EditChordFragment(val chord: Chord, val songFragment: SongFragment) : DialogFragment() {
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
        val pitchAdapter = ArrayAdapter(
            binding.root.context,
            R.layout.simple_spinner_dropdown_item, pitches
        )
        binding.pitchSpinner.adapter = pitchAdapter
        binding.pitchSpinner.setSelection(chord.interval.name.ordinal)
        binding.pitchSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chord.note = Scale.MAJOR.steps[position]
                songFragment.updateChords()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupTypeSpinner() {
        val values = mutableListOf("default")
        for (chordType in ChordType.VALUES) {
            values += chordType.toString()
        }
        val modifierAdapter = ArrayAdapter(
            binding.root.context,
            R.layout.simple_spinner_dropdown_item, values
        )
        binding.typeSpinner.adapter = modifierAdapter
        binding.typeSpinner.setSelection(
            if (chord.chordType == Scale.MAJOR.chordTypes[chord.interval.name.ordinal])
                0
            else chord.chordType.ordinal + 1
        )
        binding.typeSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 0) {
                    chord.chordType = Scale.MAJOR.chordTypes[chord.interval.name.ordinal]
                } else {
                    chord.chordType = ChordType.VALUES[position - 1]
                }
                songFragment.updateChords()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}