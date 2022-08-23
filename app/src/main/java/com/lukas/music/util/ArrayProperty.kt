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

import kotlin.reflect.*

class ArrayProperty<T>(private val array: Array<T>, private val position: Int) :
    KMutableProperty0<T> {
    override val annotations: List<Annotation> = listOf()
    override val isAbstract: Boolean = false
    override val isFinal: Boolean = false
    override val isOpen: Boolean = false
    override val isSuspend: Boolean = false
    override val name: String = "TODO"
    override val parameters: List<KParameter> = listOf()
    override val returnType: KType get() = TODO()
    override val typeParameters: List<KTypeParameter> = listOf()
    override val visibility: KVisibility? = KVisibility.PUBLIC
    override fun call(vararg args: Any?): T = TODO()
    override fun callBy(args: Map<KParameter, Any?>): T = TODO()
    override val setter: KMutableProperty0.Setter<T> get() = TODO()


    override val isConst: Boolean = false
    override val isLateinit: Boolean = false
    override val getter: KProperty0.Getter<T> get() = TODO()


    override fun getDelegate(): Any? = null
    override fun invoke(): T = TODO()

    override fun set(value: T) {
        array[position] = value
    }


    override fun get(): T = array[position]
}
