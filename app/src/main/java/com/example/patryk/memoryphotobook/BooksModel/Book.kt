package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point

class Book(
    var title:String,
    var height:Int,
    var wight:Int,
    var defaultBackgrounColor:Int,
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

    fun remove(richImage: RichImage,page: Page){
        page.remove(richImage)
    }
    fun remove(text: Text,page: Page){
        page.remove(text)
    }
    fun remove(stick: Sticker,page: Page){
        page.remove(stick)
    }

    fun addSticker(src:Bitmap,page:Page):Sticker{
        return page.addSticker(src)
    }

    fun addText(text:String,page:Page):Text{
        return page.addText(text)
    }

    fun addRichImage(src:Bitmap,page: Page):RichImage
    {
        return page.addRichImage(src)
    }

    fun setFilter(image:RichImage,filter:CFilter)
    {
        image.filter=filter.filter
    }

    fun move(richImage: RichImage,p:Point):RichImage
    {
        richImage.possition=p
        return richImage
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