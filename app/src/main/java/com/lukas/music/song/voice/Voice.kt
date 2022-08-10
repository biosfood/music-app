package com.lukas.music.song.voice

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.note.Note

abstract class Voice(val instrument: Instrument) {
    abstract val steps: List<Int>

    abstract fun step(root: Note, chord: Array<Note>)
}