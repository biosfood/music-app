package com.lukas.music.song

import com.lukas.music.instruments.Instrument

class Song(
    private val chordProgression: ChordProgression
) {
    fun step() {
        val chord = chordProgression.step()
        for (instrument in Instrument.instruments) {
            instrument.startNote(chord.root)
        }
    }

    companion object {
        var currentSong = Song(
            ChordProgression(
                listOf(
                    Chord(130.82), Chord(164.82), Chord(174.62)
                )
            )
        )
    }
}