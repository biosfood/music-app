package com.lukas.music.song.note

import kotlin.math.pow

class Note(private val id: Int) {
    private val noteName = NoteName.VALUES[id % 12]
    val octave = id / 12 - 1
    val frequency = 440 * 2.0.pow((id - 69) / 12.0)

    operator fun plus(other: Int): Note {
        if (id + other < 0 || id + other > 127) {
            throw IllegalArgumentException("cannot add $other to note with id $id")
        }
        return NOTES[id + other]
    }

    operator fun minus(other: Int): Note {
        return this + (-other)
    }

    companion object {
        val NOTES = Array(128) { Note(it) }

        fun of(noteName: NoteName, octave: Int): Note {
            return NOTES[12 * (octave + 1) + noteName.index]
        }
    }
}