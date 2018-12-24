package com.example.patryk.memoryphotobook.view.ImageListView

import android.content.Context
import android.graphics.Canvas
import android.view.View
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.Frame
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.widget.Toast

import android.view.DragEvent
import android.widget.LinearLayout
import android.view.ViewGroup
import android.graphics.PorterDuff
import android.util.Log


class SingleFrameListElement(context:Context,var frame: Frame): ImageView(context) {

    init {
        setImageBitmap(frame.bitMap)
        setOnLongClickListener(MyLongClick())
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
    class MyLongClick:View.OnLongClickListener
    {
        override fun onLongClick(v: View): Boolean {
            // Create a new ClipData.Item from the ImageView object's tag
            val item = ClipData.Item("frame")
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