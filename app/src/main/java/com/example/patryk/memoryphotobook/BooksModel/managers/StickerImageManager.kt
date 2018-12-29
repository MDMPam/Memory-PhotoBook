package com.example.patryk.memoryphotobook.BooksModel.managers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.patryk.memoryphotobook.R

class StickerImageManager(var context:Context) {
    var stickerList : Array<Bitmap> = arrayOf( BitmapFactory.decodeResource(context.resources,
        R.drawable.cat
    ) )

}