package com.example.patryk.memoryphotobook.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.Sticker
import com.example.patryk.memoryphotobook.BooksModel.Text
import com.example.patryk.memoryphotobook.MainActivity

class TextView(view:EditBookView, var text: Text):ImageView(view.context) {
    init {
        elevation=text.level.toFloat()
        setImageBitmap(text.bitmap)
        setOnTouchListener(MyTouchListener(view, this))
    }
    override fun onDraw(canvas: Canvas?) {
        setImageBitmap(text.bitmap)
        x=text.possition.x.toFloat()
        y=text.possition.y.toFloat()
        super.onDraw(canvas)
    }

    private class MyTouchListener(var view: EditBookView, var text:TextView) : View.OnTouchListener {
        var dX: Float = 0.toFloat()
        var dY:Float = 0.toFloat()
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            val X = motionEvent.getRawX().toInt()
            val Y = motionEvent.getRawY().toInt()
            when (motionEvent.getAction() and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.getX() - motionEvent.getRawX();
                    dY = view.getY() - motionEvent.getRawY();
                    return true
                }
                MotionEvent.ACTION_UP -> {
                    return true
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    return true
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    return true
                }
                MotionEvent.ACTION_MOVE -> {

                    view.animate()
                        .x(motionEvent.getRawX() + dX)
                        .y(motionEvent.getRawY() + dY)
                        .setDuration(0)
                        .start()
                    this.view.presenter.move(text.text,
                        Point(motionEvent.getRawX().toInt() + dX.toInt(),motionEvent.getRawY().toInt() + dY.toInt())
                    )
                }
            }
            //_root.invalidate()
            return false

        }
    }
}