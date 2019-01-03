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
import com.example.patryk.memoryphotobook.BooksModel.RichImage
import com.example.patryk.memoryphotobook.view.EditView.EditBookView

class RichImageView(var view: EditBookView, var richImage:RichImage):ImageView(view.context),IObjectView {
    companion object {
        const val DataDesc="RichImage"
    }

    override var image: Image
        get() = richImage
        set(value) {}
    override var highlighted: Boolean=false


    init {

        elevation=richImage.level.toFloat()
        richImage.createBitmap()
        setImageBitmap(richImage.bitmap)
        setOnLongClickListener(ImageLongClick(view))
        setImageBitmap(richImage.bitmap)
        setOnTouchListener (MyTouchListener(view,this))

    }

    override fun reSize(scale:Float) {
        richImage.resize((richImage.baseW*scale).toInt(),(richImage.baseH*scale).toInt())
    }
    override fun onDraw(canvas: Canvas?) {
        setImageBitmap(richImage.bitmap)
        this.elevation=image.level.toFloat()
        x=richImage.possition.x.toFloat()
        y=richImage.possition.y.toFloat()
        super.onDraw(canvas)
        if (this.view.selected==richImage)
            canvas?.drawCircle(10.toFloat(),10F,10F, Paint().also { it.color=Color.GREEN })
    }

    class ImageLongClick(var view:EditBookView):View.OnLongClickListener
    {

        override fun onLongClick(v: View): Boolean {
            view.selected=(v as RichImageView).richImage
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

    private class MyTouchListener(var view: EditBookView, var richImage: RichImageView) : View.OnTouchListener {
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            when (motionEvent.getAction() and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    this.view.selected= (view as RichImageView).richImage
                    return false
                }
                MotionEvent.ACTION_UP -> {return false}
                MotionEvent.ACTION_POINTER_DOWN -> {return false}
                MotionEvent.ACTION_POINTER_UP -> {return false}
                MotionEvent.ACTION_MOVE -> {return false}
            }
            //_root.invalidate()
            return false

        }
    }

}