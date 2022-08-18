package com.lukas.music.song

import com.lukas.music.song.chords.ChordType

enum class Scale(val identifier: String, val steps: Array<Int>, val chordTypes: Array<ChordType>) {
    MAJOR(
        "major",
        arrayOf(0, 2, 4, 5, 7, 9, 11, 12),
        arrayOf(
            ChordType.MAJOR,
            ChordType.MINOR,
            ChordType.MINOR,
            ChordType.MAJOR,
            ChordType.MAJOR,
            ChordType.MINOR,
            ChordType.DIMINISHED
        )
    )
}