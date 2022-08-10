package com.lukas.music.song

enum class ChordType(val notes: Array<Int>) {
    Major(arrayOf(0, 4, 7)),
    Minor(arrayOf(0, 3, 7)),
    ;
}