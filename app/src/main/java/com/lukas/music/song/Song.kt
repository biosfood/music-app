package com.lukas.music.song

import android.os.Handler
import android.os.Looper
import android.widget.RadioButton
import com.lukas.music.instruments.Instrument
import com.lukas.music.song.chords.Chord
import com.lukas.music.song.chords.ChordProgression
import com.lukas.music.song.note.Note

class Song(
    private val root: Note,
    val chordProgression: ChordProgression,
    val beats: Int
) {
    private var beat = 0
    private var chord: Chord = chordProgression.step()
    val stepButtons = mutableListOf<RadioButton>()

    fun step() {
        Handler(Looper.getMainLooper()).post {
            stepButtons[beat].isChecked = false
            beat++
            if (beat >= beats) {
                beat = 0
                chord = chordProgression.step()
            }
            stepButtons[beat].isChecked = true
            // this should not be executed here, but otherwise timing problems show up...
            val chordNotes = chord.getNotes(root)
            for (voice in Instrument.voice) {
                voice.step(root, chordNotes)
            }
        }
    }

    companion object {
        var currentSong = Song(
            Note.NOTES[69],
            ChordProgression(),
            4
        )
    }
}