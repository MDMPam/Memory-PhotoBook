package com.example.patryk.memoryphotobook

import com.example.patryk.memoryphotobook.BooksModel.*

class DisplayPresenter(var view:DisplayView, bookTitle:String) {

    var manager=BookManager(view.context)
    var scaler=BookScaler()
    var bookModel: DisplayBookModel
    init {
        bookModel= DisplayBookModel(scaler.scaleBook( manager.loadBook(bookTitle),view.width, view.height))
    }



}