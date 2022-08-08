package com.lukas.music.instruments

class MonoInstrument(name: String) : Instrument(name) {
    private val internalInstrument = InternalInstrument()

    override fun startNote(frequency: Double) {
        println("note $frequency")
        internalInstrument.startNote(frequency)
    }

    override fun changeActive(newActive: Boolean) {
        internalInstrument.active = newActive
    }
}