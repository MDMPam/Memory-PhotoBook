package com.example.patryk.memoryphotobook.BooksModel

import android.os.Environment
import android.util.Log
import java.io.File
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import java.io.FileOutputStream
import java.io.FileWriter


class BookConverter {
    companion object {
        val br="<br/>"
        private var pagNumber=0
        fun convertToHTML(conext:Context,book:Book)
        {

            //toDo delee after
            if(book.title.isEmpty())
                book.title="example book"
            pagNumber=0
            val path="${Environment.getExternalStorageDirectory()}/PhotoBook/${book.title}"
            val mydir = File(path)
            if (!mydir.exists())
                mydir.mkdirs()
            else
                Log.d("error", "dir. already exists")
            var toWrite=""
            toWrite+=openDiv(book.height,book.wight,book.coverPage.backgroundColor)
            toWrite+=pageToString(book.coverPage,path)
            toWrite+=closeDiv()+br
            pagNumber++
            book.pageList.forEach {
                toWrite+=openDiv(book.height,book.wight,it.backgroundColor)
                toWrite+=pageToString(it,path)
                toWrite+=closeDiv()+br
                pagNumber++
            }
            saveHtml(toWrite,path,book.title)

        }
        private fun openDiv(h:Int,w:Int,bg:Int?=null):String{
            return "<div style=\"width: ${w}px; height: ${h}px; position: relative; " +
                    " ${if (bg!=null){"background-color: "+ String.format("#%06X", (0xFFFFFF and bg))+";"} else{""} }\">"
        }

        fun closeDiv()="</div>"

        private fun pageToString(page:Page,path:String):String{
            var html=""
            var iterator:Int=0
            page.textList.forEach {
                val name="$pagNumber$iterator"
                createjpg(it.bitmap!!,path, name)
                html+= addImage(name,it.possition)
                iterator++
            }
            return  html
        }
        private fun addImage(name:String,pos: Point):String
        {
            return "<img src=\"${name}.png\" style=\" position: absolute; top: ${pos.y}px;" +
                    " left: ${pos.x}px;\" >"
        }
        private fun createjpg(bmp:Bitmap,path:String,name:String)
        {

            val myDir = File(path)
            myDir.mkdirs()

            val fname = "$name.png"
            val file = File(myDir, fname)
            Log.i("tworzenie bitmapy", "" + file)
            if (file.exists())
                file.delete()
            try {
                val out = FileOutputStream(file)
                bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        private fun saveHtml(body:String,path:String,name:String)
        {
            val myDir = File(path)
            myDir.mkdirs()

            val fname = "$name.html"
            val file = File(myDir, fname)
            Log.i("tworzenie bitmapy", "" + file)
            if (file.exists())
                file.delete()
            try {
                val out = FileWriter(file)
                out.append(body)
                out.flush()
                out.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }
}