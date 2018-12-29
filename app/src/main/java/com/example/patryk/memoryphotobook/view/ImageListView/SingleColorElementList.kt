package com.example.patryk.memoryphotobook.view.ImageListView

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.CFilter
import com.example.patryk.memoryphotobook.view.EditView.EditBookView

class SingleColorElementList (var editView: EditBookView, var color:Int): ImageView(editView) {

    companion object {
        const val DataDesc="SingleColorElement"
    }
    init {
        val bmOverlay = Bitmap.createBitmap(editView.defaultImage.width, editView.defaultImage.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmOverlay)
        var paint=Paint()
        paint.color=color
        canvas.drawRect(0F,0F,editView.defaultImage.width.toFloat(),editView.defaultImage.height.toFloat(),paint)
        setImageBitmap(bmOverlay)
        setOnLongClickListener(MyLongClick())
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
    class MyLongClick: View.OnLongClickListener
    {
        override fun onLongClick(v: View): Boolean {
            // Create a new ClipData.Item from the ImageView object's tag
            val item = ClipData.Item(DataDesc)
            // Create a new ClipData using the tag as a label, the plain text MIME type, and
            // the already-created item. This will create a new ClipDescription object within the
            // ClipData, and set its MIME type entry to "text/plain"
            val mimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
            val data = ClipData("tag", mimeTypes, item)
            // Instantiates the drag shadow builder.
            val dragshadow = View.DragShadowBuilder(v)
            // Starts the drag
            v.startDrag(
                data        // data to be dragged
                , dragshadow   // drag shadow builder
                , v           // local data about the drag and drop operation
                , 0          // flags (not currently used, set to 0)
            )
            return true
        }
    }

}