package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Point

class DisplayBookModel(var book:Book) {
    var currentPage=book.coverPage
    private var currentPageNumber:Int=-1
    var defaultBackgroundColor:Int
        get() {return book.defaultBackgrounColor}
        set(value) {book.defaultBackgrounColor=value}
    var backgroundColor:Int
        get() {return currentPage.backgroundColor}
        set(value) {currentPage.backgroundColor=value}

    fun nextPage()
    {
        if(!(book.pageList.size< currentPageNumber -1))
        {
         book.addPage()
        }

        currentPageNumber++
        currentPage=book.pageList[currentPageNumber]


    }
    fun previousPage()
    {
        currentPageNumber--
        if(currentPageNumber<-1) currentPageNumber=-1
        currentPage = if (currentPageNumber==-1) book.coverPage
                        else book.pageList[currentPageNumber]
    }
    fun addSticker(bitmap: Bitmap):Sticker{
        return book.addSticker(bitmap,currentPage).also { it.resize(270,120) }
    }
    fun addText(text: String):Text{
        return book.addText(text,currentPage)
    }

    fun addRichImage(src:Bitmap):RichImage
    {
        return book.addRichImage(src,currentPage).also { it.resize(270,120) }
    }

    fun move(richImage: RichImage, p: Point):RichImage
    {
        return book.move(richImage,p)
    }
    fun move(text:Text,p: Point):Text
    {
        return book.move(text,p)
    }
    fun move(stick:Sticker,p: Point):Sticker
    {
        return book.move(stick,p)
    }
    fun remove(richImage: RichImage)
    {
        book.remove(richImage,currentPage)
    }
    fun remove(text: Text)
    {
        book.remove(text,currentPage)
    }
    fun remove(stick: Sticker)
    {
        book.remove(stick,currentPage)
    }

    fun setFilter(img:RichImage,filter:CFilter):RichImage
    {
        img.filter=filter.filter
        img.createBitmap()
        return  img
    }
    fun setFrame(img:RichImage,frame:Frame?):RichImage
    {
        img.frame=frame
        img.createBitmap()
        return img
    }
    fun setLevel(image:Image,level:Int)
    {
        image.level=level
    }
    fun incLevel(image:Image)
    {
        setLevel(image,image.level+1)
    }
    fun decLevel(image:Image)
    {
        setLevel(image,image.level-1)
    }




}