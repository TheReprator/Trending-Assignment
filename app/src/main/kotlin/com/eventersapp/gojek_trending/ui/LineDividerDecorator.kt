package com.eventersapp.gojek_trending.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView


class LineDividerDecorator(context: Context, @DimenRes divider_height: Int, @ColorRes color: Int) :
    RecyclerView.ItemDecoration() {

    private val dividerHeight: Int
    private val dividerPaint: Paint = Paint()

    init {
        dividerPaint.color = ContextCompat.getColor(context, color)
        dividerHeight = context.resources.getDimensionPixelSize(divider_height)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        val left = parent.paddingLeft.toFloat()
        val right = parent.width - parent.paddingRight.toFloat()
        for (i in 0 until childCount - 1) {
            val view: View = parent.getChildAt(i)
            val top = view.bottom.toFloat()
            val bottom = view.bottom + dividerHeight.toFloat()
            c.drawRect(left, top, right, bottom, dividerPaint)
        }
    }
}