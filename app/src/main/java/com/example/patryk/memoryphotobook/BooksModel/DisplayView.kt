package com.example.patryk.memoryphotobook.BooksModel

import android.content.Context
import android.graphics.Bitmap

//import java.lang.reflect.Array

interface DisplayView {
    var context:Context
    var width:Int
    var height: Int
    var stickerList:Array<Sticker>
    var textList:Array<Text>
    var imageList:Array<Image>

    var availableSticker:Array<Bitmap>
}