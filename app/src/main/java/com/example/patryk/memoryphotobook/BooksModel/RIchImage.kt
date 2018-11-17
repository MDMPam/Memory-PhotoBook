package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Canvas

class RIchImage(source:Bitmap):Image() {
    var sourceBitmap:Bitmap=source
    var shape:PictureShape=PictureShape.None
    var filter:ColorFilter=ColorFilter.None
    var frame = PictureFrame.None

    private var colorFilter:ColorFilter?=null
    override fun createBitmap() {
        val image = Bitmap.createBitmap(sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888)
        //val canvas = Canvas(image)

        bitmap= image
    }
    private fun drawFrame(img:Bitmap):Bitmap
    {
        return img
    }

    private fun drawShape(img:Bitmap):Bitmap
    {
        return img
    }


}