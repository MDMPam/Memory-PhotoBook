package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Color
import android.graphics.Point

class PageFactory {
    companion object {
        fun buildPage(temp:PageTemplate, backgroundColor: Color?=null):Page
        {
            return  Page(Color.WHITE)
        }
        fun buildCoverPage(temp: PageTemplate,book:Book):Page
        {
            return Page(Color.WHITE).also { val text=it.addText(book.title)
            text.possition= Point((book.wight-text.wight)/2,book.height/3)
            }
        }
    }
}