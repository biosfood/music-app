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

import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.lukas.music.R
import kotlin.reflect.KMutableProperty0

fun SeekBar.setup(
    min: Int, max: Int, initialProgress: Int, callback: (Int) -> Unit
) {
    this.min = min
    this.max = max
    setOnSeekBarChangeListener(object :
        SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(
            seekBar: SeekBar,
            progress: Int, fromUser: Boolean
        ) {
            callback(progress)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar) {
        }
    })
    if (progress == initialProgress) {
        callback(initialProgress)
    }
    this.progress = initialProgress
}

fun SeekBar.smartSetup(
    min: Int,
    max: Int,
    target: KMutableProperty0<Int>,
    callback: (Int) -> Unit
) {
    setup(min, max, target.get()) {
        target.set(it)
        callback(it)
    }
}

fun Button.setupToggle(
    target: KMutableProperty0<Boolean>,
    activeColor: Int,
    inactiveColor: Int = R.color.gray_0x60,
    callback: (Boolean) -> Unit = {},
) {
    setOnClickListener {
        target.set(!target.get())
        updateToggle(target, activeColor, inactiveColor)
        callback(target.get())
    }
    updateToggle(target, activeColor, inactiveColor)
}

fun Button.updateToggle(
    target: KMutableProperty0<Boolean>,
    activeColor: Int,
    inactiveColor: Int = R.color.gray_0x60,
) {
    setBackgroundColor(
        ContextCompat.getColor(context, if (target.get()) activeColor else inactiveColor)
    )
}

fun <T> Spinner.setup(
    items: Array<T>,
    initialIndex: Int,
    callback: (Int) -> Unit = {},
) {
    val arrayAdapter = ArrayAdapter(
        context,
        android.R.layout.simple_spinner_dropdown_item, items
    )
    spinnerSetupMain<T>(arrayAdapter, initialIndex, callback)
}

fun <T> Spinner.setup(
    items: List<T>,
    initialIndex: Int,
    callback: (Int) -> Unit = {},
) {
    val arrayAdapter = ArrayAdapter(
        context,
        android.R.layout.simple_spinner_dropdown_item, items
    )
    spinnerSetupMain<T>(arrayAdapter, initialIndex, callback)
}

private fun <T> Spinner.spinnerSetupMain(
    arrayAdapter: ArrayAdapter<T>,
    initialIndex: Int,
    callback: (Int) -> Unit
) {
    adapter = arrayAdapter
    setSelection(initialIndex)
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            callback(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
}

fun <T : Enum<*>> Spinner.smartSetup(
    items: Array<T>,
    target: KMutableProperty0<T>,
    callback: (Int) -> Unit = {}
) {
    val arrayAdapter = ArrayAdapter(
        context,
        android.R.layout.simple_spinner_dropdown_item, items
    )
    spinnerSetupMain<T>(arrayAdapter, target.get().ordinal) {
        if (target.get() != items[it]) {
            target.set(items[it])
        }
        callback(it)
    }
}

fun <T> makeMoveCallback(
    list: MutableList<T>,
    callback: (Int, Int) -> Unit = { _, _ -> }
): ItemTouchHelper.SimpleCallback {
    return object : ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN,
        0
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            val adapter = recyclerView.adapter
            val startPosition = viewHolder.adapterPosition
            val endPosition = target.adapterPosition
            val item = list[startPosition]
            list.removeAt(startPosition)
            list.add(endPosition, item)
            adapter!!.notifyItemMoved(startPosition, endPosition)
            callback(startPosition, endPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}
    }
}