package com.lukas.music.song.chords

class Phrase {
    val chords = mutableListOf<Chord>(
        Chord(0, ChordType.MAJOR),
        Chord(5, ChordType.MAJOR),
        Chord(2, ChordType.MINOR),
        Chord(7, ChordType.MAJOR),
    )

    var position = 0
    fun step(parent: ChordProgression): Chord {
        var parent: ChordProgression = parent
        val result = chords[position]
        position++
        if (position >= chords.size) {
            position = 0
            parent++
        }
        return result
    }
}