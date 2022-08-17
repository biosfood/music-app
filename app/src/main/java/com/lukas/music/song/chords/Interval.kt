package com.lukas.music.song.chords

class Interval(private val distance: Int) {
    private val name: IntervalName = when (distance) {
        0 -> IntervalName.UNISON
        1, 2 -> IntervalName.SECOND
        3, 4 -> IntervalName.THIRD
        5, 6 -> IntervalName.FOURTH
        7 -> IntervalName.FIFTH
        8, 9 -> IntervalName.SIXTH
        10, 11 -> IntervalName.SEVENTH
        12 -> IntervalName.OCTAVE
        else -> throw IllegalArgumentException("cannot make interval from distance $distance")
    }
    private val modifier: Modifier = when (distance) {
        0, 5, 12 -> Modifier.PERFECT
        1, 3, 5, 8, 10 -> Modifier.MINOR
        else -> Modifier.MAJOR
    }

    override fun toString(): String {
        return name.toString()
    }

    enum class IntervalName(val distance: Int, val romanVersion: String) {
        UNISON(0, "I"),
        SECOND(1, "II"),
        THIRD(3, "III"),
        FOURTH(5, "IV"),
        FIFTH(7, "V"),
        SIXTH(8, "VI"),
        SEVENTH(10, "VII"),
        OCTAVE(12, "VIII")
        ;

        override fun toString(): String {
            return romanVersion
        }
    }

    enum class Modifier(val descriptor: String, val offset: Int) {
        PERFECT("", 0),
        MINOR("", 0),
        MAJOR("", 1);
    }

}