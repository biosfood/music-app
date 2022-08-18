package com.lukas.music.song.chords

import com.lukas.music.song.Song

class ChordProgression(val song: Song) {
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
        for (callback in Song.phraseCallback) {
            callback(phrases[position])
        }
        return this
    }
}