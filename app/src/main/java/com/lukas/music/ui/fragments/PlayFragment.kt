package com.lukas.music.ui.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        setupSlider(binding.masterVolumeSlider, 0, 100, 100) {
            setMasterVolume(it.toDouble() / 100.0)
            binding.masterVolumeText.text = "Master volume: $it%"
        }
        setupSlider(binding.tempoSlider, 50, 150, 90) {
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
            Handler(Looper.getMainLooper()).post { putChords() }
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
            putChords()
        }
        chordDisplays[Song.currentSong.chordProgression.currentItem!!.index].setCardBackgroundColor(
            ContextCompat.getColor(binding.root.context, R.color.purple_700)
        )
        chordDisplays[Song.currentSong.chordProgression.currentItem!!.indexBehind].setCardBackgroundColor(
            ContextCompat.getColor(binding.root.context, R.color.gray_0x40)
        )
    }

    private fun setupSlider(
        slider: SeekBar,
        min: Int,
        max: Int,
        initialProgress: Int,
        callback: (Int) -> Unit
    ) {
        slider.min = min
        slider.max = max
        slider.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                callback(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })
        slider.progress = initialProgress
    }

    private fun putChords() {
        binding.phraseDisplay.removeAllViews()
        chordDisplays.clear()
        for (chord in Song.currentSong.chordProgression.currentItem ?: return) {
            val card = CardView(binding.root.context)
            card.layoutParams = SongFragment.tableRowLayout
            card.radius = 10f
            card.preventCornerOverlap = false
            val text = TextView(binding.root.context)
            text.text = chord.toString(true, Song.currentSong.root)
            text.layoutParams = SongFragment.tableRowLayout
            text.textSize = 20f
            text.textAlignment = TextView.TEXT_ALIGNMENT_CENTER
            card.addView(text)
            binding.phraseDisplay.addView(card)
            chordDisplays += card
        }
        binding.phraseTable.isStretchAllColumns = true
        binding.nextChordText.text =
            Song.currentSong.chordProgression.lookahead(1)[0].toString(true, Song.currentSong.root)
    }

    private external fun setMasterVolume(volume: Double)
}