package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap

class Sticker(source:Bitmap):Image() {
    init {
        bitmap=source
    }
    override fun createBitmap() {

    }
}