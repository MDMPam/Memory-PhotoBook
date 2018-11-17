package com.example.patryk.memoryphotobook

import android.graphics.Bitmap
import android.graphics.Point
import com.example.patryk.memoryphotobook.BooksModel.*

class DisplayPresenter(var view:DisplayView, bookTitle:String) {
    var stickerManager=StickerImageManager(view.context)
    var manager=BookManager(view.context)
    var scaler=BookScaler()
    var bookModel: DisplayBookModel
    init {
        view.availableSticker=stickerManager.stickerList
    }

    init {
        bookModel= DisplayBookModel(scaler.scaleBook( manager.loadBook(bookTitle),view.width, view.height))
    }
    fun addSticker(bitmap: Bitmap):Sticker{
        var ret= bookModel.addSticker(bitmap)
        view.stickerList=bookModel.currentPage.stickerList.toTypedArray()
        return ret
    }
    fun move(sticker:Sticker,point: Point):Sticker
    {
        return bookModel.move(sticker,point)
    }


}