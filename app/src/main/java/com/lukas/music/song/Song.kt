package com.lukas.music.song

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.note.Note

class Song(
    private val root: Note,
    private val chordProgression: ChordProgression,
) {
    private var beat = 0
    private var chord: Chord = chordProgression.step()

    fun step() {
        val chordNotes = chord.getNotes(root)
        for (voice in Instrument.voice) {
                voice.step(root, chordNotes)
        }
        beat++
        if (beat > 4) {
            beat -= 4
            chord = chordProgression.step()
        }
    }

    companion object {
        var currentSong = Song(
            Note.NOTES[69],
            ChordProgression(
                listOf(
                    Chord(0, ChordType.Major),
                    Chord(5, ChordType.Major),
                    Chord(2, ChordType.Minor),
                    Chord(7, ChordType.Major),
                )
            )
        )
    }
}