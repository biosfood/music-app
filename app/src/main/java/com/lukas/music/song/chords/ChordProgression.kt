package com.lukas.music.song.chords

class ChordProgression {
    // TODO: special handler for increasing or decreasing measuresPerPhrase
    val measuresPerPhrase: Int = 4
    val phrases = mutableListOf<Phrase>()

    private var position = 0
    fun step(): Chord {
        val phrase = phrases[position]
        return phrase.step(this)
    }

    operator fun inc(): ChordProgression {
        position++
        position %= phrases.size
        return this
    }
}