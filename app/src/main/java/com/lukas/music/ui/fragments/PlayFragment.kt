package com.lukas.music.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.lukas.music.R
import com.lukas.music.databinding.FragmentPlayBinding
import com.lukas.music.instruments.Rhythm
import com.lukas.music.song.Song
import com.lukas.music.song.Song.Companion.setOnBeatCallback
import com.lukas.music.song.Song.Companion.setOnChordCallback
import com.lukas.music.song.Song.Companion.setOnPhraseCallback
import com.lukas.music.song.chords.Chord

class PlayFragment : Fragment() {
    lateinit var binding: FragmentPlayBinding
    private val beatIndicators = mutableListOf<RadioButton>()
    private val chordDisplays = mutableMapOf<Chord, CardView>()

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
        binding.masterVolumeSlider.min = 0
        binding.masterVolumeSlider.max = 100
        binding.masterVolumeSlider.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                setMasterVolume(progress.toDouble() / 100.0)
                binding.masterVolumeText.text = "Master volume: $progress%"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        binding.masterVolumeSlider.progress = 100
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
        binding.root.setOnBeatCallback { before, now ->
            beatIndicators[before].isChecked = false
            beatIndicators[now].isChecked = true
        }
        binding.root.setOnPhraseCallback {
            binding.phraseDisplay.removeAllViews()
            chordDisplays.clear()
            for (chord in it.chords) {
                val card = CardView(binding.root.context)
                val text = TextView(binding.root.context)
                text.text = chord.toString(true, Song.currentSong.root)
                card.layoutParams = SongFragment.layout
                card.addView(text)
                binding.phraseDisplay.addView(card)
                chordDisplays[chord] = card
            }
            binding.phraseTable.isStretchAllColumns = true
        }
        binding.root.setOnChordCallback { old, new ->
            chordDisplays[old]?.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.gray_600
                )
            )
            chordDisplays[new]?.setBackgroundColor(
                ContextCompat.getColor(
                    binding.root.context,
                    R.color.gray_400
                )
            )
        }
        return binding.root
    }

    external fun setMasterVolume(volume: Double)
}