package com.example.peopledaliy.test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class MyView:View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()
        paint.color=Color.RED
        canvas!!.drawRect(0f,0f,300f,300f,paint)
    }
    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.e("MyView", "dispatchTouchEvent: ")
        return super.dispatchTouchEvent(event)
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("MyView", "onTouchEvent: ")
        return super.onTouchEvent(event)
    }
}