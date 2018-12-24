package com.example.patryk.memoryphotobook.BooksModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.patryk.memoryphotobook.R

class FrameManager(var context: Context) {
    var frameList : Array<Frame> = arrayOf( Frame(BitmapFactory.decodeResource(context.resources, R.drawable.frame))
    ,Frame(BitmapFactory.decodeResource(context.resources, R.drawable.frame)),
        Frame(BitmapFactory.decodeResource(context.resources, R.drawable.frame)),
        Frame(BitmapFactory.decodeResource(context.resources, R.drawable.frame)),
        Frame(BitmapFactory.decodeResource(context.resources, R.drawable.frame)))
}