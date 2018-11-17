package com.example.patryk.memoryphotobook.BooksModel

import android.content.Context
import android.graphics.Color

class BookManager(context:Context) {

    fun getBookList():Array<String>
    {
        return arrayOf()
    }

    fun loadBook(name : String):Book
    {
        return Book("",0,0, Color(),PageTemplate.None)
    }

    fun saveBook(book:Book)
    {

    }


}