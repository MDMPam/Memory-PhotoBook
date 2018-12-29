package com.example.patryk.memoryphotobook.view.ImageListView

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View
import android.widget.ImageView
import com.example.patryk.memoryphotobook.view.EditView.EditBookView

class SingleLevelElementList (var editView: EditBookView, var level:Int): ImageView(editView) {

    companion object {
        const val DataDesc="SingleLevelElement"
    }
    init {
        var paint=Paint()
        paint.textSize=200.toFloat()
        val baseline = -(paint).ascent() // ascent() is negative
        val width = ((paint).measureText(level.toString()) + 0.5f).toInt() // round
        val height = (baseline + (paint as Paint).descent() + 0.5f).toInt()
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(level.toString(), 0.toFloat(), baseline, paint!!)
        setImageBitmap(image)
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