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
    this.progress = initialProgress
}

fun Button.setupToggle(target: KMutableProperty0<Boolean>, activeColor: Int) {
    setOnClickListener {
        target.set(!target.get())
        setBackgroundColor(
            ContextCompat.getColor(context, if (target.get()) activeColor else R.color.gray_0x60)
        )
    }
    setBackgroundColor(
        ContextCompat.getColor(context, if (target.get()) activeColor else R.color.gray_0x60)
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
        target.set(items[it])
    }
}