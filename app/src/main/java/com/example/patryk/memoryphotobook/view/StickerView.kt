package com.example.patryk.memoryphotobook.view

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.util.Log
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.patryk.memoryphotobook.BooksModel.DisplayView
import com.example.patryk.memoryphotobook.BooksModel.Sticker
import com.example.patryk.memoryphotobook.MainActivity

class StickerView(view:MainActivity, var sticker:Sticker):ImageView(view.context) {
    var movable=false
    init {

        setImageBitmap(sticker.bitmap)
        setOnTouchListener(MyTouchListener(view,this))

    }
    override fun onDraw(canvas: Canvas?) {
        //setImageBitmap(sticker.bitmap)
        x=sticker.possition.x.toFloat()
        y=sticker.possition.y.toFloat()
        clearAnimation()
        super.onDraw(canvas)
    }
    class MyTouchListener(var view:MainActivity,var sticker:StickerView) : View.OnTouchListener {
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
                    this.view.presenter.move(sticker.sticker,Point(motionEvent.getRawX().toInt() + dX.toInt(),motionEvent.getRawY().toInt() + dY.toInt()))
                }
            }
            //_root.invalidate()
            return false

        }
    }

}


