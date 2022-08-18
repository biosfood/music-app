package com.lukas.music.song.chords

import com.lukas.music.util.MetaCycle

class ChordProgression : MetaCycle<Phrase>() {
    override fun add(element: Phrase): Boolean {
        if (size == 0) {
            for (callback in stepCallback) {
                // first step
                callback()
            }
        }
        return super.add(element)
    }
}