package com.lukas.music.instruments

enum class Waveform(val id: Int, private val identifier: String) {
    SINE(0, "sine"),
    SAWTOOTH(1, "sawtooth"),
    ;

    override fun toString(): String {
        return identifier
    }

    companion object {
        val VALUES = values()
        val DEFAULT = SINE
    }
}