package com.lukas.music.song.chords

class Phrase {
    val chords = mutableListOf<Chord>(
        Chord(0, ChordType.Major),
        Chord(5, ChordType.Major),
        Chord(2, ChordType.Minor),
        Chord(7, ChordType.Major),
    )

    var position = 0
    fun step(parent: ChordProgression): Chord {
        var parent: ChordProgression = parent
        position++
        if (position >= chords.size) {
            position = 0
            parent++
        }
        return chords[position]
    }
}