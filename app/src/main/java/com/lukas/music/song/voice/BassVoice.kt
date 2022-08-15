package com.lukas.music.song.voice

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.note.Note

class BassVoice(instrument: Instrument) : Voice(instrument) {
    override val steps = listOf(1, 3)

    override fun step(root: Note, chord: Array<Note>) {
        instrument.startNote(chord[0] - 24)
    }
}