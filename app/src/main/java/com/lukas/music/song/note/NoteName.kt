package com.lukas.music.song.note

import kotlin.math.pow

const val A4 = 440.0

enum class NoteName(val index: Int, val asString: String) {
    C(0, "C"),
    Csharp(1, "C#"),
    D(2, "D"),
    Dsharp(3, "D#"),
    E(4, "E"),
    F(5, "F"),
    Fsharp(6, "F#"),
    G(7, "G"),
    Gsharp(8, "G#"),
    A(9, "A"),
    Asharp(10, "A#"),
    B(11, "B"),
    ;

    val baseFrequency: Double = A4 * 2.0.pow(index.toDouble() / 12)

    operator fun plus(interval: Int): NoteName {
        val resultPosition = (index + interval) % VALUES.size
        return VALUES[resultPosition]
    }

    override fun toString(): String {
        return asString
    }

    companion object {
        val VALUES = values()
    }
}