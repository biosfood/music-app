package com.lukas.music.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import com.lukas.music.databinding.FragmentSongBinding
import com.lukas.music.song.Song


class SongFragment : Fragment() {
    private lateinit var binding: FragmentSongBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSongBinding.inflate(inflater)
        binding.chords.isStretchAllColumns = true
        val layout = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT
        )
        layout.setMargins(10)
        for (phrase in Song.currentSong.chordProgression.phrases) {
            val row = TableRow(binding.root.context)
            for (chord in phrase.chords) {
                val card = CardView(binding.root.context)
                card.radius = 10f
                card.layoutParams = layout
                val text = TextView(binding.root.context)
                text.text = chord.toString()
                text.layoutParams = layout
                card.addView(text)
                row.addView(card)
            }
            binding.chords.addView(row)
        }
        return binding.root
    }
}