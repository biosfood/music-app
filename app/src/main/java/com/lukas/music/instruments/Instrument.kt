package com.lukas.music.instruments

import com.lukas.music.databinding.FragmentInstrumentBinding

class Instrument(val name: String, var frequency: Double) {
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
        binding.activeSwitch.setOnCheckedChangeListener { _, newActive ->
            active = newActive
            setInstrumentActive(id, newActive)
        }
        binding.activeSwitch.isChecked = active
    }

    fun startNote(frequency: Double) {
        startNote(id, frequency)
    }

    fun endNote(frequency: Double) {
        endNote(id, frequency)
    }

    private external fun createInstrument(): Int
    private external fun setInstrumentFrequency(id: Int, frequency: Double)
    private external fun setInstrumentActive(id: Int, isActive: Boolean)
    private external fun startNote(id: Int, frequency: Double)
    private external fun endNote(id: Int, frequency: Double)

    companion object {
        val instruments =
            mutableListOf<Instrument>(
                Instrument("A", 440.0),
                Instrument("C#", 440 * 1.25),
                Instrument("E", 440 * 1.5),
            )
    }
}