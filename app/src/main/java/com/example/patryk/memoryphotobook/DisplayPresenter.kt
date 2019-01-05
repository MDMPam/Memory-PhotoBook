package com.example.patryk.memoryphotobook

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.graphics.Typeface
import com.example.patryk.memoryphotobook.BooksModel.*
import com.example.patryk.memoryphotobook.BooksModel.managers.*

class DisplayPresenter(var view:DisplayView, bookTitle:String,pageTemplate: PageTemplate?=null, width:Int?=null,height:Int?=null) {
    var stickerManager= StickerImageManager(view.context)
    var manager= BookManager(view.context)
    var frameManager= FrameManager(view.context)
    var scaler=BookScaler()
    var bookModel: DisplayBookModel
    var photoManager = PhotoManager(view.context)
    init {
        val initWidth=if (width!=null)width else view.width
        val initheight=if (height!=null)height else view.height
        view.avalblePhotoLst=photoManager.photoList
        view.avalibleFrame=frameManager.frameList
        view.availableSticker=stickerManager.stickerList
        view.avalibleFilterr= ColorFilterManagerr().filterArray
        view.avalbleColor=ColorManager().colorArray
        view.avaiableFont=FontManager(view.context).fontArray
        var book=BookManager(view.context).loadBook(bookTitle)
        if(book==null) book=Book(bookTitle,initheight,initWidth, Color.WHITE,pageTemplate!!)
        bookModel= DisplayBookModel(book)
        view.backgroundColor=bookModel.backgroundColor

        refreshView()


    }

    private fun refreshView()
    {
        view.imageList=bookModel.currentPage.richImageList.toTypedArray()
        view.textList=bookModel.currentPage.textList.toTypedArray()
        view.stickerList=bookModel.currentPage.stickerList.toTypedArray()
        view.backgroundColor=bookModel.currentPage.backgroundColor
    }



    fun addSticker(bitmap: Bitmap):Sticker{
        //todo change it /becose of init height
        bookModel.book.height=view.height
        bookModel.book.wight=view.width


        var ret= bookModel.addSticker(bitmap)
        view.stickerList=bookModel.currentPage.stickerList.toTypedArray()
        return ret
    }
    fun addText(text:String):Text{
        //todo change it /becose of init height
        bookModel.book.height=view.height
        bookModel.book.wight=view.width


        var ret= bookModel.addText(text)
        view.textList=bookModel.currentPage.textList.toTypedArray()
        return ret
    }
    fun addRichImage(srcBitmap: Bitmap):RichImage{
        //todo change it /becose of init height
        bookModel.book.height=view.height
        bookModel.book.wight=view.width


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
        refreshView()
    }

    fun previousPage()
    {
        bookModel.previousPage()
        refreshView()
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
    fun setColor(text: Text,color:Int)
    {
        text.color=color
    }

    fun saveAsHtml()
    {
        bookModel.book.height=view.height
        bookModel.book.wight=view.width
        BookConverter.convertToHTML(bookModel.book)
    }
    fun saveAsPDF()
    {
        bookModel.book.height=view.height
        bookModel.book.wight=view.width
        BookConverter.convertToPDF(bookModel.book)
    }


}