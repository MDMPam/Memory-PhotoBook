package com.example.patryk.memoryphotobook.view

import android.content.Context
import android.graphics.Canvas
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.Sticker

class DisplayedSticker(context:Context, var sticker:Sticker):ImageView(context) {
    init {
        setImageBitmap(sticker.bitmap)
    }
    override fun onDraw(canvas: Canvas?) {
        setImageBitmap(sticker.bitmap)
        x=sticker.possition.x.toFloat()
        y=sticker.possition.y.toFloat()
        super.onDraw(canvas)
    }
}