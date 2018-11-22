package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth



class RichImage(source:Bitmap):Image() {
    var frame:Frame?=null
    var sourceBitmap:Bitmap=source
    var shape:PictureShape=PictureShape.None
    var filter:ColorFilter=ColorFilter.None

    private var colorFilter:ColorFilter?=null
    override fun createBitmap() {

        if(frame!=null) {
            val bmOverlay = Bitmap.createBitmap(sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bmOverlay)
            canvas.drawBitmap(sourceBitmap, Matrix(), null)

            canvas.drawBitmap(Bitmap.createScaledBitmap((frame as Frame).bitMap, sourceBitmap.width, sourceBitmap.height, false), 0.toFloat(), 0.toFloat(), null)
            bitmap= bmOverlay
        }
        else
            bitmap= sourceBitmap
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