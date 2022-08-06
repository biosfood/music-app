package com.lukas.music.instruments

import java.util.*
import kotlin.concurrent.schedule

object Rhythm {
    var on = false
    fun start() {
        Timer().schedule(0, 500) {
            on = !on
            Instrument.instruments.forEach {
                if (on) {
                    it.startNote(it.frequency)
                } else {
                    it.endNote(it.frequency)
                }
            }
        }
    }
}