/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.instruments

import com.lukas.music.song.Song
import java.util.*
import kotlin.concurrent.schedule

object Rhythm {
    var on: Boolean = false
        set(on) {
            field = on
            for (instrument in Instrument.instruments) {
                instrument.stop()
            }
        }

    private val callback: TimerTask.() -> Unit = {
        if (on) {
            Song.currentSong.step()
        }
    }

    private val timer = Timer()
    private lateinit var task: TimerTask

    fun setTempo(tempo: Int) {
        if (this::task.isInitialized) {
            task.cancel()
        }
        task = timer.schedule((60000 / tempo).toLong(), (60000 / tempo).toLong(), callback)
    }
}