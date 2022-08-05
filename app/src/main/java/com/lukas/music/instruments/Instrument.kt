package com.lukas.music.instruments

import com.lukas.music.databinding.FragmentInstrumentBinding

class Instrument(val name: String, frequency: Double) {
    private val id = createInstrument()
    private var active = false

    init {
        print("id: $id")
        setInstrumentFrequency(id, frequency)
    }

    fun applyToView(binding: FragmentInstrumentBinding) {
        binding.instrumentNameText.text = name
        binding.floatingActionButton2.setOnClickListener {
            println("click instrument $name")
        }
        binding.activeSwitch.setOnCheckedChangeListener {_, newActive ->
            active = newActive
            setInstrumentActive(id, newActive)
        }
        binding.activeSwitch.isChecked = active
    }

    private external fun createInstrument(): Int
    private external fun setInstrumentFrequency(id: Int, frequency: Double)
    private external fun setInstrumentActive(id: Int, isActive: Boolean)
}