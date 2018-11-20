package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.Point

class DisplayBookModel(var book:Book) {
    var currentPage=book.coverPage
    private var currentPageNumber:Int=-1
    fun nextPage()
    {
        if(book.pageList.size< currentPageNumber -1)
        {
            currentPageNumber++
            currentPage=book.pageList[currentPageNumber]
        }
    }
    fun previousPage()
    {
        currentPageNumber--
        if(currentPageNumber<-1) currentPageNumber=-1
        currentPage = if (currentPageNumber==-1) book.coverPage
                        else book.pageList[currentPageNumber]
    }
    fun addSticker(bitmap: Bitmap):Sticker{
        return book.addSticker(bitmap,currentPage)
    }
    fun addText(text: String):Text{
        return book.addText(text,currentPage)
    }
    fun move(text:Text,p: Point):Text
    {
        return book.move(text,p)
    }
    fun move(stick:Sticker,p: Point):Sticker
    {
        return book.move(stick,p)
    }



}