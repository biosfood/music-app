package com.lukas.music.song

import android.os.Handler
import android.os.Looper
import android.view.View
import com.lukas.music.instruments.Instrument
import com.lukas.music.song.chords.Chord
import com.lukas.music.song.chords.ChordProgression
import com.lukas.music.song.chords.Phrase
import com.lukas.music.song.note.Note

class Song(
    var root: Note,
    val beats: Int
) {
    val chordProgression = ChordProgression(this)
    private var beat = beats - 1
    private lateinit var chord: Chord

    fun step() {
        if (chordProgression.phrases.isEmpty()) {
            return
        }
        Handler(Looper.getMainLooper()).post {
            val before = beat
            beat++
            if (beat >= beats) {
                beat = 0
                var oldChord: Chord? = null
                if (this::chord.isInitialized) {
                    oldChord = chord
                }
                chord = chordProgression.step()
                for (callback in chordCallback) {
                    callback(oldChord ?: chord, chord)
                }
            }
            for (callback in beatCallback) {
                callback(before, beat)
            }
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
            4
        )

        private val beatCallback = mutableListOf<(Int, Int) -> Unit>()
        private val chordCallback = mutableListOf<(Chord, Chord) -> Unit>()
        val phraseCallback = mutableListOf<(Phrase) -> Unit>()

        fun View.setOnBeatCallback(callback: (Int, Int) -> Unit) {
            beatCallback += callback
        }

        fun View.setOnPhraseCallback(callback: (Phrase) -> Unit) {
            phraseCallback += callback
        }

        fun View.setOnChordCallback(callback: (Chord, Chord) -> Unit) {
            chordCallback += callback
        }
    }
}