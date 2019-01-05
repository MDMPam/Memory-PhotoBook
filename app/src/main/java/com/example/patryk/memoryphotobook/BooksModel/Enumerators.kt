package com.example.patryk.memoryphotobook.BooksModel

import java.lang.Exception

enum class PictureShape(val value:Int){None(0)}



enum class ColorFilter(val value:Int){None(0)}

enum class PageTemplate(val value:Int){None(0);
companion object {
    fun fromInt(i:Int):PageTemplate
    {
        PageTemplate.values().forEach {
            if (i==it.value)
                return it
        }
        throw Exception("brak szablonu o takim numerze")
    }
}}
