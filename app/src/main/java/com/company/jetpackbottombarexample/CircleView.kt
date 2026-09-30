package com.company.jetpackbottombarexample

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.RED

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = resolveSize(200, widthMeasureSpec)
        val height = resolveSize(200, heightMeasureSpec)
        setMeasuredDimension(width, height)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val radius = width.coerceAtMost(height) / 2f
        canvas.drawCircle(
            width / 2f,
            height / 2f,
            radius,
            paint
        )
    }
}