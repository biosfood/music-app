package com.lukas.music.instruments

import com.lukas.music.databinding.FragmentInstrumentBinding

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

    abstract fun startNote(frequency: Double)
    abstract fun changeActive(newActive: Boolean)

    companion object {
        val instruments =
            mutableListOf<Instrument>(
                MonoInstrument("Bass"),
            )
    }
}