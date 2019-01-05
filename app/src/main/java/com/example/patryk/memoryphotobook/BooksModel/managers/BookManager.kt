package com.example.patryk.memoryphotobook.BooksModel.managers

import android.content.Context
import android.graphics.Color
import com.example.patryk.memoryphotobook.BooksModel.Book
import com.example.patryk.memoryphotobook.BooksModel.PageTemplate

class BookManager(context:Context) {

    fun getBookList():Array<String>
    {
        return arrayOf()
    }

    fun loadBook(name : String): Book?
    {
        return null
    }

    fun saveBook(book: Book)
    {

    }


}