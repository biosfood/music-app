package com.lukas.music.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import com.lukas.music.databinding.FragmentSongBinding
import com.lukas.music.song.Song
import com.lukas.music.song.chords.Phrase
import com.lukas.music.song.note.Note
import com.lukas.music.song.note.NoteName


class SongFragment : Fragment(), AdapterView.OnItemSelectedListener {
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
                card.layoutParams = layout
                card.setOnClickListener {
                    EditChordFragment(chord, this).showNow(childFragmentManager, "")
                }
                val text = TextView(binding.root.context)
                text.text = chord.toString(displayChordNames, Song.currentSong.root)
                text.layoutParams = layout
                card.addView(text)
                row.addView(card)
            }
            val button = ImageButton(binding.root.context)
            button.setOnClickListener {
                Song.currentSong.chordProgression -= phrase
                updateChords()
            }
            button.setImageResource(android.R.drawable.ic_delete)
            button.layoutParams = buttonLayout
            row.addView(button)
            binding.chords.addView(row)
        }
    }

    companion object {
        val layout = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT
        )
        val buttonLayout = TableRow.LayoutParams(
            0,
            TableRow.LayoutParams.WRAP_CONTENT
        )

        init {
            layout.setMargins(10)
        }
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