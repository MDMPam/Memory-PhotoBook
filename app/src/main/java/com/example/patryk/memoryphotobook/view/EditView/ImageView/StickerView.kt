package com.example.patryk.memoryphotobook.view.EditView.ImageView

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.Image
import com.example.patryk.memoryphotobook.BooksModel.Sticker
import com.example.patryk.memoryphotobook.view.EditView.EditBookView

class StickerView(var view: EditBookView, var sticker:Sticker):ImageView(view.context),IObjectView {
    var movable=false
    companion object {
        const val DataDesc="StickerView"
    }

    override var highlighted: Boolean = false
    override var image: Image
        get() = sticker
        set(value) {}

    override fun reSize(scale: Float) {
        sticker.resize((sticker.baseW*scale).toInt(),(sticker.baseH*scale).toInt())
    }
    init {
        elevation=sticker.level.toFloat()
        setImageBitmap(sticker.bitmap)
        setOnLongClickListener(StickerLongClick(view))
        setOnTouchListener(MyTouchListener(view,this))
        setImageBitmap(sticker.bitmap)

    }
    override fun onDraw(canvas: Canvas?) {
        this.elevation=image.level.toFloat()
        setImageBitmap(sticker.bitmap)
        x=sticker.possition.x.toFloat()
        y=sticker.possition.y.toFloat()
        //clearAnimation()

        super.onDraw(canvas)
        if (view.selected==sticker)
            canvas?.drawCircle(10.toFloat(),10F,10F, Paint().also { it.color= Color.GREEN })
    }

    class StickerLongClick(var view:EditBookView):View.OnLongClickListener
    {
        override fun onLongClick(v: View): Boolean {
            view.selected=(v as StickerView).sticker
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
    private class MyTouchListener(var view: EditBookView, var sticker: StickerView) : View.OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {

            when (motionEvent.getAction() and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                  this.view.selected=sticker.sticker
                    return false
                }

            }
            //_root.invalidate()
            return false

        }
    }

}


