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

    var baseW:Int
    var baseH:Int

     var resizedHeight:Int=source.height
     var resizedWidth=source.width
    init {
        baseH=source.height
        baseW=source.width
        bitmap=sourceBitmap
    }

    private var colorFilter:ColorFilter?=null
    override fun createBitmap() {
        val bmOverlay = Bitmap.createBitmap(resizedWidth, resizedHeight, Bitmap.Config.ARGB_8888)
        drawImage(bmOverlay)
        drawFrame(bmOverlay)
        bitmap=bmOverlay
    }
    private fun drawImage(bmOverlay:Bitmap)
    {
        var paint: Paint?=null
        if(filter!=null)
        {
            paint=Paint()
            paint?.colorFilter=filter
        }

        val canvas = Canvas(bmOverlay)
        canvas.drawBitmap(Bitmap.createScaledBitmap(sourceBitmap, resizedWidth, resizedHeight, false), Matrix(), paint)
    }

    private fun drawFrame(bmOverlay: Bitmap)
    {
        if(frame!=null) {
            val canvas = Canvas(bmOverlay)
            canvas.drawBitmap(
                Bitmap.createScaledBitmap(
                    (frame as Frame).bitMap,
                    resizedWidth,
                    resizedHeight,
                    false
                ), 0.toFloat(), 0.toFloat(), null
            )
        }
    }

    override fun resize(width: Int, height: Int) {
        resizedWidth=width
        resizedHeight=height
        createBitmap()
    }

    private fun drawShape(img:Bitmap):Bitmap
    {
        return img
    }


}