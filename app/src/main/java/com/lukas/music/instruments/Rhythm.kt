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