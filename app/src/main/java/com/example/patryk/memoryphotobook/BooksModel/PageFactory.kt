package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Color

class PageFactory {
    companion object {
        fun buildPage(temp:PageTemplate, backgroundColor: Color?=null):Page
        {
            return  Page(Color())
        }
        fun buildCoverPage(temp: PageTemplate):Page
        {
            return Page(Color())
        }
    }
}