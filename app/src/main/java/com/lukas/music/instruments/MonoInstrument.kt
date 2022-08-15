package com.lukas.music.instruments

import com.lukas.music.song.note.Note

class MonoInstrument(name: String) : Instrument(name) {
    private val internalInstrument = InternalInstrument()

    override var waveform: Waveform = Waveform.SINE
        set(value) {
            field = value
            internalInstrument.waveform = value
        }

    override fun startNote(note: Note) {
        internalInstrument.startNote(note.frequency)
    }

    override fun changeActive(newActive: Boolean) {
        internalInstrument.active = newActive
    }

    override fun stop() {
        internalInstrument.endNote()
    }
}