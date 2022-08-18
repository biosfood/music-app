package com.lukas.music.song.chords

import com.lukas.music.util.Cycle

class Phrase : Cycle<Chord>() {
    init {
        for (i in 0 until 4) {
            this += Chord(0, ChordType.MAJOR)
        }
    }
}