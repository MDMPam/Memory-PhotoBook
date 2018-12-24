package com.example.patryk.memoryphotobook

import android.graphics.Bitmap
import android.graphics.Point
import com.example.patryk.memoryphotobook.BooksModel.*

class DisplayPresenter(var view:DisplayView, bookTitle:String) {
    var stickerManager=StickerImageManager(view.context)
    var manager=BookManager(view.context)
    var frameManager=FrameManager(view.context)
    var scaler=BookScaler()
    var bookModel: DisplayBookModel
    init {
        view.frameList=frameManager.frameList
        view.availableSticker=stickerManager.stickerList
        bookModel= DisplayBookModel(scaler.scaleBook( manager.loadBook(bookTitle),view.width, view.height))
    }


    fun addSticker(bitmap: Bitmap):Sticker{
        var ret= bookModel.addSticker(bitmap)
        view.stickerList=bookModel.currentPage.stickerList.toTypedArray()
        return ret
    }
    fun addText(text:String):Text{
        var ret= bookModel.addText(text)
        view.textList=bookModel.currentPage.textList.toTypedArray()
        return ret
    }
    fun addRichImage(srcBitmap: Bitmap):RichImage{
        var ret= bookModel.addRichImage(srcBitmap)
        view.imageList=bookModel.currentPage.richImageList.toTypedArray()
        return ret
    }
    fun move(text:Text,point: Point):Text
    {
        return bookModel.move(text,point)
    }
    fun move(img:RichImage,point: Point):RichImage
    {
        return bookModel.move(img,point)
    }

    fun move(sticker:Sticker,point: Point):Sticker
    {
        return bookModel.move(sticker,point)
    }
    fun setframe(img:RichImage,frame:Frame?):RichImage
    {
        return bookModel.setFrame(img,frame)
    }

    fun incLevel(image:Image)
    {
        bookModel.incLevel(image)
    }

    fun decLevel(image:Image)
    {
        bookModel.decLevel(image)
    }


}