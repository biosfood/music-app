/*
 * Copyright (C) 2022 Lukas Eisenhauer
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or(at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.lukas.music.util

open class Cycle<T>(initialSize: Int = 0) : ArrayList<T>(initialSize) {

    var index = 0
    val stepCallback = mutableListOf<() -> Unit>()
    val wraparoundListeners = mutableListOf<() -> Unit>()
    val indexBehind: Int
        get() = (index - 1 + size) % size

    val currentItem: T?
        get() = if (size == 0) null else this[index]

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

    open fun reset() {
        index = size - 1
        step()
    }

    open fun reverse() {
        if (size == 0) {
            return
        }
        index = indexBehind
        // TODO: back around handlers
        for (callback in stepCallback) {
            callback()
        }
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
        this[index].step()
        if (this[index].index == 0) {
            super.step()
        }
        for (callback in miniStepCallback) {
            callback()
        }
        return this[index]
    }

    override fun reset() {
        this[index].reset()
        super.reset()
    }

    fun bigStep(keepSubindex: Boolean = false) {
        val subindex = currentItem?.index ?: return
        currentItem?.index = currentItem!!.size - 1
        step()
        if (keepSubindex) {
            currentItem?.index = subindex
            currentItem?.index = currentItem?.indexBehind ?: return
            currentItem?.step()
            for (callback in miniStepCallback) {
                callback()
            }
        }
    }

    override fun reverse() {
        currentItem?.reverse() ?: return
        if (currentItem!!.index == currentItem!!.size - 1) {
            currentItem!!.reset()
            super.reverse()
            currentItem!!.index = currentItem!!.size - 1
        }
        for (callback in miniStepCallback) {
            callback()
        }
    }

    fun bigReverse(keepSubindex: Boolean = false) {
        val subindex = currentItem?.index ?: return
        currentItem?.reset()
        index = indexBehind
        index = indexBehind
        currentItem?.index = currentItem!!.size - 1
        step()
        if (keepSubindex) {
            currentItem?.index = subindex
            currentItem?.index = currentItem!!.indexBehind
            currentItem?.step()
            for (callback in miniStepCallback) {
                callback()
            }
        }
    }
}