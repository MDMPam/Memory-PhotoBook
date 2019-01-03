package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap

class Sticker(source:Bitmap):Image() {
    var baseW=source.width
    var baseH=source.height
    private var source:Bitmap
    init {
        this.source=source
        bitmap=source
    }

    override fun createBitmap() {

    }

    override fun resize(width: Int, height: Int) {
        bitmap = Bitmap.createScaledBitmap(source, width, height, false)
    }
}