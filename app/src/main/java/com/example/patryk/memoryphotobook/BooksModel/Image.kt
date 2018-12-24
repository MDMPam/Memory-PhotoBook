package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Point

abstract class Image {

    var bitmap:Bitmap?=null
    var possition:Point=Point(0,0)
    var wight:Int=0
    var height:Int=0
    var level:Int=1

    abstract fun createBitmap()
}