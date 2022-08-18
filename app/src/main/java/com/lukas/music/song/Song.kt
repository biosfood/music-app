package com.lukas.music.song

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.chords.Chord
import com.lukas.music.song.chords.ChordProgression
import com.lukas.music.song.note.Note
import com.lukas.music.util.Cycle

class Song(
    var root: Note,
    val beats: Int
) : Cycle<Int>(beats) {
    val chordProgression = ChordProgression()
    private lateinit var chord: Chord

    init {
        for (i in 0 until beats) {
            this += i
        }
        wraparoundListeners += {
            chordProgression.step()?.let { chord = it.currentItem }
        }
    }

    override fun step(): Int {
        super.step()
        if (!this::chord.isInitialized) {
            return index
        }
        val chordNotes = chord.getNotes(root)
        for (voice in Instrument.voice) {
            voice.step(root, chordNotes)
        }
        return index
    }

    companion object {
        var currentSong = Song(
            Note.NOTES[69],
            4
        )
    }
}