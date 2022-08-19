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

import android.widget.Button
import android.widget.SeekBar
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