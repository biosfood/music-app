package com.lukas.music.instruments

import com.lukas.music.song.Song
import java.util.*
import kotlin.concurrent.schedule

object Rhythm {
    var on = false
    fun start() {
        Timer().schedule(0, 500) {
            on = !on
            Song.currentSong.step()
        }
    }
}