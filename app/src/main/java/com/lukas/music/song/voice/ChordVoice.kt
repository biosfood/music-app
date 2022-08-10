package com.lukas.music.song.voice

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.note.Note

class ChordVoice(instrument: Instrument) : Voice(instrument) {
    override val steps: List<Int> = listOf(2, 4)

    override fun step(root: Note, chord: Array<Note>) {
        instrument.stop()
        for (note in chord) {
            instrument.startNote(note)
        }
    }
}