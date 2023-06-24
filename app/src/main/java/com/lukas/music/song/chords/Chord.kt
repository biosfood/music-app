/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.song.chords

import com.lukas.music.song.Song
import com.lukas.music.song.note.Note

class Chord {
    var accidental = Accidental.None
    val accidentals: Array<Accidental?> = arrayOf(Accidental.None, Accidental.None, null, null)

    var note: Int = 0
        set(value) {
            field = value
            interval = Interval(value)
        }

    var interval = Interval(note)
        set(value) {
            field = value
            if (note != value.distance) {
                note = value.distance
            }
        }

    fun getNotes(root: Note): Array<Note> {
        val result = Array(NOTE_COUNT) { root }
        var resultIndex = 0
        var accidentalIndex = 0
        var octave = 0
        while (resultIndex < NOTE_COUNT) {
            if (accidentalIndex == 0) {
                result[resultIndex] = root + note + 12 * octave + accidental.distance
                resultIndex++
            } else if (accidentals[accidentalIndex - 1] != null) {
                result[resultIndex] = root + note + when (accidentalIndex) {
                    1 -> 4
                    2 -> 7
                    3 -> 10
                    4 -> 14
                    else -> 0
                } + accidentals[accidentalIndex - 1]!!.distance + 12 * octave + accidental.distance
                resultIndex++
            }
            accidentalIndex++
            if (accidentalIndex > accidentals.size) {
                octave++
                accidentalIndex = 0
            }
        }
        return result
    }

    override fun toString(): String {
        return toString(false, Song.currentSong.root)
    }

    fun toString(displayChordNames: Boolean, root: Note): String {
        var result = if (displayChordNames) {
            (root + note + accidental.distance).noteName.toString()
        } else {
            interval.toString()
        }
        accidentals[0]?.let {
            result += when (it) {
                Accidental.Flat -> "-"
                Accidental.Sharp -> "sus4"
                else -> ""
            }
        }
        accidentals[1]?.let {
            if (accidentals[0] != null && it == Accidental.None) {
                return@let
            }
            result += it.short + "5"
        }
        result = result.replace("-b5", "0")
        result = result.replace("(?=[A-G])#5".toRegex(), "+")
        accidentals[2]?.let {
            result += when (it) {
                Accidental.Sharp -> " maj7"
                Accidental.None -> " 7"
                Accidental.Flat -> " 6"
            }
        }
        accidentals[3]?.let {
            result += when (it) {
                Accidental.Sharp -> " ♯9"
                Accidental.None -> " 9"
                Accidental.Flat -> " ♭9"
            }
        }
        return result
    }

    companion object {
        const val NOTE_COUNT = 5
    }
}