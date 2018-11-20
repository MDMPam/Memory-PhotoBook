package com.example.patryk.memoryphotobook.view

import android.content.Context
import android.graphics.Canvas
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.Text

class TextView(context:Context,var text: Text):ImageView(context) {
    init {
        setImageBitmap(text.bitmap)
    }
    override fun onDraw(canvas: Canvas?) {
        setImageBitmap(text.bitmap)
        x=text.possition.x.toFloat()
        y=text.possition.y.toFloat()
        super.onDraw(canvas)
    }
}