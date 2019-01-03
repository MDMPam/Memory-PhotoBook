package com.example.patryk.memoryphotobook.BooksModel.managers

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import com.example.patryk.memoryphotobook.BooksModel.CFilter

class ColorFilterManagerr {

    var filterArray:Array<CFilter>
    init {
        filterArray = arrayOf(
            CFilter(
                PorterDuffColorFilter(
                    Color.RED,
                    PorterDuff.Mode.DARKEN
                )
            ),
            CFilter(
                PorterDuffColorFilter(
                    Color.RED,
                    PorterDuff.Mode.ADD
                )
            ),

            CFilter(
                PorterDuffColorFilter(
                    Color.BLACK,
                    PorterDuff.Mode.DARKEN
                )
            ),
            CFilter(
                PorterDuffColorFilter(
                    Color.BLACK,
                    PorterDuff.Mode.ADD
                )
            ),

            CFilter(
                PorterDuffColorFilter(
                    Color.BLUE,
                    PorterDuff.Mode.DARKEN
                )
            ),
            CFilter(
                PorterDuffColorFilter(
                    Color.BLUE,
                    PorterDuff.Mode.ADD
                )
            ),

            CFilter(
                PorterDuffColorFilter(
                    Color.CYAN,
                    PorterDuff.Mode.DARKEN
                )
            ),
            CFilter(
                PorterDuffColorFilter(
                    Color.CYAN,
                    PorterDuff.Mode.ADD
                )
            ),

            CFilter(
                PorterDuffColorFilter(
                    Color.DKGRAY,
                    PorterDuff.Mode.DARKEN
                )
            ),
            CFilter(
                PorterDuffColorFilter(
                    Color.DKGRAY,
                    PorterDuff.Mode.ADD
                )
            ),

            CFilter(
                PorterDuffColorFilter(
                    Color.GRAY,
                    PorterDuff.Mode.DARKEN
                )
            ),
            CFilter(
                PorterDuffColorFilter(
                    Color.GRAY,
                    PorterDuff.Mode.ADD
                )
            ),

            CFilter(
                PorterDuffColorFilter(
                    Color.GREEN,
                    PorterDuff.Mode.DARKEN
                )
            ),
            CFilter(
                PorterDuffColorFilter(
                    Color.GREEN,
                    PorterDuff.Mode.ADD
                )
            )
        )
    }
}