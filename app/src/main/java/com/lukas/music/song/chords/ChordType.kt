package com.lukas.music.song.chords

enum class ChordType(
    val notes: Array<Int>,
    private val asString: String,
    val transform: (String) -> String
) {
    MAJOR(arrayOf(0, 4, 7), "major", { it.uppercase() }),
    MINOR(arrayOf(0, 3, 7), "minor", { it.lowercase() }),
    DIMINISHED(arrayOf(0, 3, 6), "diminished", { it.lowercase() + "0" }),
    ;

    override fun toString(): String {
        return asString
    }

    companion object {
        val VALUES = values()
    }
}