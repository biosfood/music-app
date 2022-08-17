package com.lukas.music.song.chords

enum class ChordType(
    val notes: Array<Int>,
    private val asString: String,
    val transform: (String) -> String
) {
    Major(arrayOf(0, 4, 7), "major", { it.uppercase() }),
    Minor(arrayOf(0, 3, 7), "minor", { it.lowercase() }),
    ;

    override fun toString(): String {
        return asString
    }
}