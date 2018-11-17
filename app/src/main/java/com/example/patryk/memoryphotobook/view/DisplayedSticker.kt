package com.example.patryk.memoryphotobook.view

import android.content.Context
import android.graphics.Canvas
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.Sticker

class DisplayedSticker(context:Context, var sticker:Sticker):ImageView(context) {
    override fun onDraw(canvas: Canvas?) {
        setImageBitmap(sticker.bitmap)
        super.onDraw(canvas)
    }
}