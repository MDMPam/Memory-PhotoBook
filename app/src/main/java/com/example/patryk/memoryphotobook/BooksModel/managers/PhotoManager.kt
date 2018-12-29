package com.example.patryk.memoryphotobook.BooksModel.managers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.patryk.memoryphotobook.R

class PhotoManager(var context: Context) {
    var photoList:Array<Bitmap> = arrayOf()

    init {
        photoList= arrayOf(
            BitmapFactory.decodeResource(context.resources, R.drawable.graf1),
            BitmapFactory.decodeResource(context.resources, R.drawable.graph2),
            BitmapFactory.decodeResource(context.resources, R.drawable.graph3),
            BitmapFactory.decodeResource(context.resources, R.drawable.graph4))
    }
}