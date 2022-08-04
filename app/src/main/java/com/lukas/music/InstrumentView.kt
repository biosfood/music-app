package com.lukas.music

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.lukas.music.ui.fragments.InstrumentFragment

class InstrumentView : View {
    val fragment = InstrumentFragment()

    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.InstrumentView, defStyle, 0
        )

        a.recycle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paddingLeft = paddingLeft
        val paddingTop = paddingTop
        val paddingRight = paddingRight
        val paddingBottom = paddingBottom

        val contentWidth = width - paddingLeft - paddingRight
        val contentHeight = height - paddingTop - paddingBottom
        // fragment.view?.left = paddingLeft
        // fragment.view?.top = paddingTop
        // fragment.view?.right = paddingLeft + contentWidth
        // fragment.view?.bottom = paddingTop + contentHeight
        fragment.view?.draw(canvas)
    }
}