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
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Space
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.lukas.music.R
import com.lukas.music.databinding.FragmentPlayBinding
import com.lukas.music.instruments.Rhythm
import com.lukas.music.song.Song
import com.lukas.music.util.UIUtil
import com.lukas.music.util.setup

class PlayFragment : Fragment() {
    private lateinit var binding: FragmentPlayBinding
    private val beatIndicators = mutableListOf<RadioButton>()
    private val chordDisplays = mutableListOf<CardView>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPlayBinding.inflate(inflater)
        binding.playButton.setOnClickListener {
            Rhythm.on = !Rhythm.on
            binding.playButton.setImageResource(
                if (Rhythm.on) android.R.drawable.ic_media_pause else android.R.drawable.ic_media_play
            )
        }
        binding.advancePhraseButton.setOnClickListener {
            Song.currentSong.chordProgression.bigStep(true)
        }
        binding.reversePhraseButton.setOnClickListener {
            Song.currentSong.chordProgression.bigReverse(true)
        }
        binding.advanceMeasureButton.setOnClickListener {
            Song.currentSong.chordProgression.step()
        }
        binding.reverseMeasureButton.setOnClickListener {
            Song.currentSong.chordProgression.currentItem?.let {
                chordDisplays[it.index].setCardBackgroundColor(
                    ContextCompat.getColor(binding.root.context, R.color.gray_0x40)
                )
            }
            Song.currentSong.chordProgression.reverse()
        }
        binding.masterVolumeSlider.setup(0, 100, 100) {
            setMasterVolume(it.toDouble() / 100.0)
            binding.masterVolumeText.text = "Master volume: $it%"
        }
        binding.tempoSlider.setup(50, 150, 90) {
            Rhythm.setTempo(it)
            binding.tempoText.text = "tempo: ${it}bpm"
        }
        setupBeatIndicator()
        Song.currentSong.stepCallback += {
            Handler(Looper.getMainLooper()).post {
                beatIndicators[Song.currentSong.indexBehind].isChecked = false
                beatIndicators[Song.currentSong.index].isChecked = true
            }
        }
        Song.currentSong.chordProgression.stepCallback += {
            Handler(Looper.getMainLooper()).post { updateChords() }
        }
        Song.currentSong.chordProgression.miniStepCallback += {
            Handler(Looper.getMainLooper()).post { updateChordView() }
        }
        return binding.root
    }

    private fun setupBeatIndicator() {
        val layout = RadioGroup.LayoutParams(
            RadioGroup.LayoutParams.WRAP_CONTENT,
            RadioGroup.LayoutParams.MATCH_PARENT
        )
        layout.weight = 1.0f
        val spacer = Space(binding.root.context)
        spacer.layoutParams = layout
        binding.beatIndicator.addView(spacer)
        for (i in 0 until Song.currentSong.beats) {
            val child = RadioButton(binding.root.context)
            child.layoutParams = layout
            child.isClickable = false
            if (i == 0) {
                child.isChecked = true
            }
            beatIndicators += child
            binding.beatIndicator.addView(child)
        }
    }

    private fun updateChordView() {
        if (chordDisplays.isEmpty()) {
            updateChords()
            return
        }
        chordDisplays[Song.currentSong.chordProgression.currentItem!!.index].setCardBackgroundColor(
            ContextCompat.getColor(binding.root.context, R.color.purple_700)
        )
        chordDisplays[Song.currentSong.chordProgression.currentItem!!.indexBehind].setCardBackgroundColor(
            ContextCompat.getColor(binding.root.context, R.color.gray_0x40)
        )
    }

    fun updateChords() {
        binding.phraseDisplay.removeAllViews()
        chordDisplays.clear()
        for (chord in Song.currentSong.chordProgression.currentItem ?: return) {
            val card = CardView(binding.root.context)
            card.layoutParams = UIUtil.cardLayout
            card.radius = 10f
            card.preventCornerOverlap = false
            val text = TextView(binding.root.context)
            text.text = chord.toString(true, Song.currentSong.root)
            text.layoutParams = UIUtil.fillingLayout
            text.textSize = 20f
            text.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            card.addView(text)
            binding.phraseDisplay.addView(card)
            chordDisplays += card
        }
        binding.phraseTable.isStretchAllColumns = true
        binding.nextChordText.text =
            Song.currentSong.chordProgression.lookahead(1)[0].toString(true, Song.currentSong.root)
        updateChordView()
    }

    private external fun setMasterVolume(volume: Double)
}