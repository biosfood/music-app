package com.lukas.music.instruments

class InternalInstrument {
    private val id = createInstrument()
    var active: Boolean = false
        get() = active
        set(value) {
            field = value
            setInstrumentActive(id, value)
        }

    fun startNote(frequency: Double) {
        startNote(id, frequency)
    }

    fun endNote() {
        endNote(id)
    }

    private external fun createInstrument(): Int
    private external fun setInstrumentActive(id: Int, isActive: Boolean)
    private external fun startNote(id: Int, frequency: Double)
    private external fun endNote(id: Int)
}