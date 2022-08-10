package com.lukas.music.instruments

import com.lukas.music.databinding.FragmentInstrumentBinding
import com.lukas.music.song.note.Note
import com.lukas.music.song.voice.BassVoice
import com.lukas.music.song.voice.ChordVoice
import com.lukas.music.song.voice.Voice

abstract class Instrument(private var name: String) {
    private var active = false

    fun applyToView(binding: FragmentInstrumentBinding) {
        binding.instrumentNameText.text = name
        binding.floatingActionButton2.setOnClickListener {
            println("click instrument $name")
        }
        binding.activeSwitch.setOnCheckedChangeListener { _, newActive ->
            active = newActive
            changeActive(newActive)
        }
        binding.activeSwitch.isChecked = active
    }

    abstract fun startNote(note: Note)
    abstract fun stop()
    abstract fun changeActive(newActive: Boolean)

    companion object {
        val instruments =
            mutableListOf<Instrument>(
                MonoInstrument("Bass"),
                PolyInstrument("Chords"),
            )

        val voice = mutableListOf<Voice>(
            BassVoice(instruments[0]),
            ChordVoice(instruments[1]),
        )
    }
}