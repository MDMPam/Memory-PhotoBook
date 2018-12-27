package com.example.patryk.memoryphotobook.view

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Point
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import com.example.patryk.memoryphotobook.BooksModel.RichImage
import com.example.patryk.memoryphotobook.view.EditView.EditBookView

class RichImageView(view: EditBookView, var richImage:RichImage):ImageView(view.context) {
    companion object {
        const val DataDesc="RichImage"
    }
    init {
        elevation=richImage.level.toFloat()
        richImage.createBitmap()
        setImageBitmap(richImage.bitmap)
        setOnLongClickListener(ImageLongClick())
        setImageBitmap(richImage.bitmap)

    }
    override fun onDraw(canvas: Canvas?) {
        setImageBitmap(richImage.bitmap)
        x=richImage.possition.x.toFloat()
        y=richImage.possition.y.toFloat()
        super.onDraw(canvas)
    }

    class ImageLongClick:View.OnLongClickListener
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

    private class MyTouchListener(var view: EditBookView, var richImage:RichImageView) : View.OnTouchListener {
        var dX: Float = 0.toFloat()
        var dY:Float = 0.toFloat()
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            val X = motionEvent.getRawX().toInt()
            val Y = motionEvent.getRawY().toInt()
            when (motionEvent.getAction() and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.getX() - motionEvent.getRawX();
                    dY = view.getY() - motionEvent.getRawY();
                    return true
                }
                MotionEvent.ACTION_UP -> {
                    var x=0
                    return true
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                    return true
                }
                MotionEvent.ACTION_POINTER_UP -> {
                    return true
                }
                MotionEvent.ACTION_MOVE -> {

                    view.animate()
                        .x(motionEvent.getRawX() + dX)
                        .y(motionEvent.getRawY() + dY)
                        .setDuration(0)
                        .start()
                    this.view.presenter.move(richImage.richImage,
                        Point(motionEvent.getRawX().toInt() + dX.toInt(),motionEvent.getRawY().toInt() + dY.toInt())
                    )
                }
            }
            //_root.invalidate()
            return false

        }
    }

}