package com.lukas.music.song.chords

import com.lukas.music.song.note.Note

class Chord(note: Int, var chordType: ChordType) {
    var note: Int = note
        set(value) {
            field = value
            interval = Interval(value)
        }
    var interval = Interval(note)

    fun getNotes(root: Note): Array<Note> {
        return Array(chordType.notes.size) { root + note + chordType.notes[it] }
    }

    override fun toString(): String {
        return chordType.transform(interval.toString())
    }

    fun toString(displayChordNames: Boolean, root: Note): String {
        val base = if (displayChordNames) {
            (root + note).noteName.toString()
        } else {
            interval.toString()
        }
        return chordType.transform(base)
    }
}