package com.lukas.music.song

class ChordProgression(val chords: List<Chord>) {
    private var index = 0

    fun step(): Chord {
        val result = chords[index]
        index++
        index %= chords.size
        return result
    }
}