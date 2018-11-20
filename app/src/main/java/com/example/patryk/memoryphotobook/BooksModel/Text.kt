package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Paint
import android.graphics.Bitmap
import android.R.attr.textColor
import android.R.attr.textSize
import android.graphics.Canvas


class Text(var text:String=""):Image() {

    var paint: Paint=Paint()
    init {
        paint.textSize=200.toFloat()
    }
    override fun createBitmap() {

            val baseline = -(paint).ascent() // ascent() is negative
            val width = ((paint).measureText(text) + 0.5f).toInt() // round
            val height = (baseline + (paint as Paint).descent() + 0.5f).toInt()
            val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(image)
            canvas.drawText(text, 0.toFloat(), baseline, paint!!)
            bitmap= image

    }
}