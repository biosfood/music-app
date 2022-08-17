package com.lukas.music.song.chords

import com.lukas.music.song.note.Note

class Chord(val note: Int, private val chordType: ChordType) {
    fun getNotes(root: Note): Array<Note> {
        return Array(chordType.notes.size) { root + note + chordType.notes[it] }
    }

    override fun toString(): String {
        return "$note($chordType)"
    }

    fun toString(displayChordNames: Boolean, root: Note): String {
        if (displayChordNames) {
            val noteName = (root + note).noteName
            if (chordType == ChordType.Major) {
                return noteName.toString().uppercase()
            }
            return noteName.toString().lowercase()
        }
        return toString()
    }
}