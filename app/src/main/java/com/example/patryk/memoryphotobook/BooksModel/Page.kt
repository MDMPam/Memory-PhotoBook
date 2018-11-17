package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Color

class Page(
    val backgroundColor: Color
) {
    val stickerList:MutableList<Sticker> = mutableListOf()
    val textList:MutableList<Text> = mutableListOf()
    val richImageList:MutableList<RIchImage> = mutableListOf()

    internal val template:PageTemplate=PageTemplate.None

    fun addSticker(sticker:Sticker){
        stickerList.add(sticker)
    }

    fun addText(text:Text){
        textList.add(text)
    }

    fun addRichImage(image:RIchImage){
        richImageList.add(image)
    }

}