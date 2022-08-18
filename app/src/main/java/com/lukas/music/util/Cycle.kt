package com.lukas.music.util

open class Cycle<T>(initialSize: Int = 0) : ArrayList<T>(initialSize) {

    var index = 0
    val stepCallback = mutableListOf<() -> Unit>()
    val wraparoundListeners = mutableListOf<() -> Unit>()
    val indexBehind: Int
        get() = (index - 1 + size) % size

    val currentItem: T
        get() = this[index]

    open fun step(): T? {
        if (size == 0) {
            return null
        }
        index++
        if (index >= size) {
            index %= size
            for (callback in wraparoundListeners) {
                callback()
            }
        }
        for (callback in stepCallback) {
            callback()
        }
        return this[index]
    }

    fun lookahead(distance: Int): T {
        return this[(index + distance) % size]
    }

    fun lookbehind(distance: Int): T {
        return lookahead(-distance)
    }
}

open class MetaCycle<T : Cycle<out Any>> : Cycle<T>() {
    val miniStepCallback = mutableListOf<() -> Unit>()

    override fun step(): T? {
        if (size == 0) {
            return null
        }
        val callback: () -> Unit = {
            super.step()
        }
        this[index].wraparoundListeners += callback
        this[index].step()
        this[index].wraparoundListeners -= callback
        for (callback in miniStepCallback) {
            callback()
        }
        return this[index]
    }
}