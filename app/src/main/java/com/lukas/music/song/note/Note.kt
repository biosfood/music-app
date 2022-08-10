package com.lukas.music.song.note

import kotlin.math.pow

class Note(val id: Int) {
    private val noteName = NoteName.VALUES[id % NoteName.VALUES.size]
    val octave = id / NoteName.VALUES.size - 1
    val frequency = noteName.baseFrequency * 2.0.pow(id / NoteName.VALUES.size - 5)

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
            return NOTES[NoteName.VALUES.size * (octave + 1) + noteName.index]
        }
    }
}