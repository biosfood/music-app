package com.lukas.music.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
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
        for (phrase in Song.currentSong.chordProgression.phrases) {
            val row = TableRow(binding.root.context)
            for (chord in phrase.chords) {
                val text = TextView(binding.root.context)
                text.text = chord.toString()
                row.addView(text)
            }
            binding.chords.addView(row)
        }
        return binding.root
    }
}