package com.example.patryk.memoryphotobook.view

import android.content.Context
import android.graphics.Canvas
import android.view.View
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.RichImage

class RichImageView(context:Context,var richImage:RichImage): ImageView(context) {
    init {

        setImageBitmap(richImage.bitmap)

    }
    override fun onDraw(canvas: Canvas?) {
        setImageBitmap(richImage.bitmap)
        x=richImage.possition.x.toFloat()
        y=richImage.possition.y.toFloat()
        super.onDraw(canvas)
    }
}