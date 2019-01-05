package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter

class CFilter(var color: Int,var mode: PorterDuff.Mode) {
    var filter: PorterDuffColorFilter
        get() {return PorterDuffColorFilter(color,mode)}
        set(value) {}
}