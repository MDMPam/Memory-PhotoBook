package com.example.patryk.memoryphotobook

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class StickerImageManager(var context:Context) {
    var stickerList : Array<Bitmap> = arrayOf( BitmapFactory.decodeResource(context.resources, R.drawable.cat) )

}