package com.example.patryk.memoryphotobook.BooksModel

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
}