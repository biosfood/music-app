package com.lukas.music.song

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.chords.ChordProgression
import com.lukas.music.song.note.Note
import com.lukas.music.util.Cycle

class Song(
    var root: Note,
    val beats: Int
) : Cycle<Int>(beats) {
    val chordProgression = ChordProgression()

    init {
        for (i in 0 until beats) {
            this += i
        }
        wraparoundListeners += {
            chordProgression.step()
        }
    }

    override fun step(): Int {
        super.step()
        val chord = chordProgression.currentItem?.currentItem ?: return index
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