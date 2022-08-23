/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.song.voice

import com.lukas.music.instruments.Instrument
import com.lukas.music.song.note.Note

class BassVoice(instrument: Instrument) : Voice(instrument) {
    override var noteActive: Array<Array<Boolean>> = arrayOf(
        arrayOf(true),
        arrayOf(false),
        arrayOf(true),
        arrayOf(false)
    )

    override val noteCount: Int = 1

    override fun getNotes(root: Note, chordNotes: Array<Note>): Array<Note> {
        return arrayOf(chordNotes[0] - 24)
    }
}