package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint

class Page(
    val backgroundColor: Color
) {
    val stickerList:MutableList<Sticker> = mutableListOf()
    val textList:MutableList<Text> = mutableListOf()
    val richImageList:MutableList<RichImage> = mutableListOf()

    internal var template:PageTemplate=PageTemplate.None

    fun addSticker(source:Bitmap):Sticker{
        template=PageTemplate.None
        var stick = Sticker(source)
        stickerList.add(stick)
        return stick
    }

    fun addText(text:String):Text{
        var ret=Text(text)
        ret.createBitmap()
        textList.add(ret)
        return ret
    }

    fun addRichImage(source:Bitmap):RichImage{
        var image=RichImage(source)
        richImageList.add(image)
        return image
    }

}