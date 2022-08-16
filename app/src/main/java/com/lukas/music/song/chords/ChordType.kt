package com.lukas.music.song.chords

enum class ChordType(val notes: Array<Int>, val asString: String) {
    Major(arrayOf(0, 4, 7), "major"),
    Minor(arrayOf(0, 3, 7), "minor"),
    ;

    override fun toString(): String {
        return asString
    }
}