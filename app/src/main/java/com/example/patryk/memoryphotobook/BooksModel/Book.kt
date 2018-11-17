package com.example.patryk.memoryphotobook.BooksModel

import android.graphics.Color

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

}