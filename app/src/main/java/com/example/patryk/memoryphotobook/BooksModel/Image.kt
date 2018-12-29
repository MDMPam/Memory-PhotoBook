package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Point
import java.lang.Exception

abstract  open class Image {

    var bitmap:Bitmap?=null
    var possition:Point=Point(0,0)
    var wight:Int
        get() {return try{bitmap!!.width}catch (e:Exception){0}}
        set(value) {}
    var height:Int
        get() {return try{bitmap!!.height}catch (e:Exception){0}}
        set(value) {}
    var level:Int=1

    abstract fun createBitmap()
    open fun resize(width:Int,height:Int)
    {

        

    }
}