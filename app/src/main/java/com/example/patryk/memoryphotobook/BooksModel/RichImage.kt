package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.*
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import android.graphics.PorterDuff
import android.support.v4.content.ContextCompat
import android.graphics.PorterDuffColorFilter





class RichImage(source:Bitmap):Image() {
    var frame:Frame?=null
    var sourceBitmap:Bitmap=source
    var shape:PictureShape=PictureShape.None
    var filter:PorterDuffColorFilter?=null

    init {
        bitmap=sourceBitmap
    }



    private var colorFilter:ColorFilter?=null
    override fun createBitmap() {
        var paint: Paint?=null

        if(filter!=null)
        {
            paint=Paint()
            paint?.colorFilter=filter
        }

        if(frame!=null) {
            val bmOverlay = Bitmap.createBitmap(sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmOverlay)
            canvas.drawBitmap(sourceBitmap, Matrix(), paint)

            canvas.drawBitmap(Bitmap.createScaledBitmap((frame as Frame).bitMap, sourceBitmap.width, sourceBitmap.height, false), 0.toFloat(), 0.toFloat(), null)
            bitmap= bmOverlay
        }
        else {
            val bmOverlay = Bitmap.createBitmap(sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmOverlay)
            canvas.drawBitmap(sourceBitmap, Matrix(), paint)
            bitmap = bmOverlay
        }
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