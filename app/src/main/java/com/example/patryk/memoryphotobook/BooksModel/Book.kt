package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point

class Book(
    var title:String,
    var height:Int,
    var wight:Int,
    var defaultBackgrounColor:Color,
    var defaultPageTemplate:PageTemplate)
{
    var coverPage:Page=PageFactory.buildCoverPage(defaultPageTemplate)
    var pageList:MutableList<Page> = mutableListOf()

    fun addPage(template:PageTemplate=defaultPageTemplate)
    {
        pageList.add(PageFactory.buildPage(template))
    }
    fun addPage(page:Page)
    {
        pageList.add(page)
    }

    fun addSticker(src:Bitmap,page:Page):Sticker{
        return page.addSticker(src)
    }

    fun addText(text:String,page:Page):Text{
        return page.addText(text)
    }
    fun move(text:Text,p:Point):Text
    {
        text.possition= p
        return text
    }
    fun move(stick:Sticker,p:Point):Sticker
    {
        stick.possition=p
        return stick
    }

}