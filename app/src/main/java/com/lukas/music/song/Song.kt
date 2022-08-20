/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
    var soloInstrument: Instrument? = null
        set(value) {
            field = value
            value?.let {
                for (instrument in Instrument.instruments) {
                    if (instrument != value) {
                        instrument.stop()
                    }
                }
            }
        }

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
        soloInstrument?.let {
            it.voice.step(root, chordNotes)
        } ?: run {
            for (voice in Instrument.voice) {
                voice.step(root, chordNotes)
            }
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