package com.example.patryk.memoryphotobook

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import com.example.patryk.memoryphotobook.BooksModel.*
import com.example.patryk.memoryphotobook.BooksModel.managers.*

class DisplayPresenter(var view:DisplayView, bookTitle:String) {
    var stickerManager= StickerImageManager(view.context)
    var manager= BookManager(view.context)
    var frameManager= FrameManager(view.context)
    var scaler=BookScaler()
    var bookModel: DisplayBookModel
    var photoManager = PhotoManager(view.context)
    init {

        view.avalblePhotoLst=photoManager.photoList
        view.avalibleFrame=frameManager.frameList
        view.availableSticker=stickerManager.stickerList
        view.avalibleFilterr= ColorFilterManagerr().filterArray
        view.avalbleColor=ColorManager().colorArray
        bookModel= DisplayBookModel(scaler.scaleBook( manager.loadBook(bookTitle),view.width, view.height))
        view.backgroundColor=bookModel.backgroundColor
        view.avaiableFont=FontManager(view.context).fontArray
        bookModel.book.title="book1"
        bookModel.book.height=view.height
        bookModel.book.wight=view.width
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
        val tr=bookModel.move(img,point)
        view.stickerList=bookModel.currentPage.stickerList.toTypedArray()
        return tr
    }

    fun move(sticker:Sticker,point: Point):Sticker
    {
        return bookModel.move(sticker,point)
    }
    fun setFilter(img:RichImage,filter:CFilter):RichImage
    {
        return bookModel.setFilter(img, filter)
    }
    fun setframe(img:RichImage,frame:Frame?):RichImage
    {
        return bookModel.setFrame(img,frame)
        view.imageList=bookModel.currentPage.richImageList.toTypedArray()
    }

    fun remove(richImage: RichImage)
    {
        bookModel.remove(richImage)
        view.imageList=bookModel.currentPage.richImageList.toTypedArray()
    }
    fun remove(text: Text)
    {
        bookModel.remove(text)
        view.textList=bookModel.currentPage.textList.toTypedArray()
    }
    fun remove(sticker: Sticker)
    {
        bookModel.remove(sticker)
        view.stickerList=bookModel.currentPage.stickerList.toTypedArray()
    }

    fun incLevel(image:Image)
    {
        bookModel.incLevel(image)
    }

    fun decLevel(image:Image)
    {
        bookModel.decLevel(image)
    }

    fun setLevel(image: Image,lvl:Int)
    {
        image.level=lvl
    }
    fun setBackgroundColor(color:Int)
    {
        bookModel.backgroundColor=color
        view.backgroundColor=bookModel.backgroundColor
    }
    fun nextPage()
    {
        bookModel.nextPage()
        view.imageList=bookModel.currentPage.richImageList.toTypedArray()
        view.textList=bookModel.currentPage.textList.toTypedArray()
        view.stickerList=bookModel.currentPage.stickerList.toTypedArray()
        view.backgroundColor=bookModel.currentPage.backgroundColor
    }

    fun previousPage()
    {
        bookModel.previousPage()
        view.imageList=bookModel.currentPage.richImageList.toTypedArray()
        view.textList=bookModel.currentPage.textList.toTypedArray()
        view.stickerList=bookModel.currentPage.stickerList.toTypedArray()
        view.backgroundColor=bookModel.currentPage.backgroundColor
    }
    fun setTypeface(text: Text,typeface: Typeface):Text
    {
        text.font=typeface
        return  text
    }

    fun setHref(image: Image,href:String):Image
    {
        image.href=href
        return image
    }

    fun saveAsHtml()
    {
        bookModel.book.height=view.height
        bookModel.book.wight=view.width
        BookConverter.convertToHTML(view.context,bookModel.book)
    }


}