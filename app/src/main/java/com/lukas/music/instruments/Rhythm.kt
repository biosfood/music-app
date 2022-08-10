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

    fun start() {
        Timer().schedule(0, 500) {
            if (on) {
                Song.currentSong.step()
            }
        }
    }
}