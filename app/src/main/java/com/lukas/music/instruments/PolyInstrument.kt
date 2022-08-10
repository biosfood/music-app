package com.lukas.music.instruments

import com.lukas.music.song.note.Note

class PolyInstrument(name: String) : Instrument(name) {
    private val internalInstruments = Array(3) { InternalInstrument() }
    private val playing = Array(3) { false }

    override fun startNote(note: Note) {
        for ((index, instrumentPlaying) in playing.withIndex()) {
            if (!instrumentPlaying) {
                internalInstruments[index].startNote(note.frequency)
                playing[index] = true
                return
            }
        }
        throw IllegalStateException("cannot start another note with the current amount of oscillators")
    }

    override fun changeActive(newActive: Boolean) {
        for (instrument in internalInstruments) {
            instrument.active = newActive
        }
    }

    override fun stop() {
        for ((i, instrument) in internalInstruments.withIndex()) {
            instrument.endNote()
            playing[i] = false
        }
    }
}