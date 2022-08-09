package com.lukas.music.song

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.note.Note
import com.lukas.music.song.note.NoteName

class Song(
    private val root: Note,
    private val chordProgression: ChordProgression,
) {
    fun step() {
        val chord = chordProgression.step()
        for (instrument in Instrument.instruments) {
            instrument.startNote(root + chord.note)
        }
    }

    companion object {
        var currentSong = Song(
            Note.of(NoteName.F, 4),
            ChordProgression(
                listOf(
                    Chord(1, ChordType.Major),
                    Chord(6, ChordType.Major),
                    Chord(2, ChordType.Minor),
                    Chord(8, ChordType.Major),
                )
            )
        )

        init {
            println("root:  ${currentSong.root.frequency}")
        }
    }
}