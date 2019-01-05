package com.example.patryk.memoryphotobook.BooksModel.managers

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import com.example.patryk.memoryphotobook.BooksModel.CFilter

class ColorFilterManagerr {

    companion object {
        fun parseToString(filter:CFilter):String{
            return "${filter.color},${filter.mode}"
        }
        fun getFilterFromString(str:String):CFilter
        {
            val arg=str.split(",")
            return CFilter(arg[0].toInt(),PorterDuff.Mode.valueOf(arg[1]))
        }
    }
    var filterArray:Array<CFilter>
    init {
        filterArray = arrayOf(
            CFilter( Color.RED,PorterDuff.Mode.DARKEN),
            CFilter(Color.RED,PorterDuff.Mode.ADD),
            CFilter(Color.BLACK,PorterDuff.Mode.DARKEN),
            CFilter(Color.BLACK,PorterDuff.Mode.ADD),
            CFilter(Color.BLUE,PorterDuff.Mode.DARKEN),
            CFilter(Color.BLUE,PorterDuff.Mode.ADD),
            CFilter(Color.CYAN, PorterDuff.Mode.DARKEN),
            CFilter(Color.CYAN,PorterDuff.Mode.ADD),
            CFilter(Color.DKGRAY,PorterDuff.Mode.DARKEN),
            CFilter(Color.DKGRAY,PorterDuff.Mode.ADD),
            CFilter(Color.GRAY,PorterDuff.Mode.DARKEN),
            CFilter(Color.GRAY,PorterDuff.Mode.ADD),
            CFilter(Color.GREEN,PorterDuff.Mode.DARKEN),
            CFilter(Color.GREEN,PorterDuff.Mode.ADD )

        )
    }
}