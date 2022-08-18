package com.lukas.music.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Space
import androidx.fragment.app.Fragment
import com.lukas.music.databinding.FragmentPlayBinding
import com.lukas.music.instruments.Rhythm
import com.lukas.music.song.Song
import com.lukas.music.song.Song.Companion.setOnBeatCallback

class PlayFragment : Fragment() {
    lateinit var binding: FragmentPlayBinding
    val beatIndicators = mutableListOf<RadioButton>()

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
        Song.currentSong.chordDisplay = binding.currentChord
        binding.root.setOnBeatCallback { before, now ->
            beatIndicators[before].isChecked = false
            beatIndicators[now].isChecked = true
        }
        return binding.root
    }

    external fun setMasterVolume(volume: Double)
}