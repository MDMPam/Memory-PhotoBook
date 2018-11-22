package com.example.patryk.memoryphotobook.view

import android.content.ClipData
import android.content.ClipDescription
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.patryk.memoryphotobook.BooksModel.Sticker

class StickerView(context:Context, var sticker:Sticker):ImageView(context) {
    init {

        setImageBitmap(sticker.bitmap)
        this.setOnDragListener(StickerOnDragListener(this))
        this.setOnLongClickListener(StickerLongClickListener())
    }
    override fun onDraw(canvas: Canvas?) {
        setImageBitmap(sticker.bitmap)
        x=sticker.possition.x.toFloat()
        y=sticker.possition.y.toFloat()
        super.onDraw(canvas)
    }

    private class MyDragShadowBuilder(v: View) : View.DragShadowBuilder(v) {

        private val shadow = ColorDrawable(Color.LTGRAY)
        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            val width: Int = view.width / 2
            val height: Int = view.height / 2
            shadow.setBounds(0, 0, width, height)
            touch.set(width / 2, height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            // Draws the ColorDrawable in the Canvas passed in from the system.
            shadow.draw(canvas)
        }
    }

    class StickerOnDragListener(var view:StickerView):View.OnDragListener
    {
        override fun onDrag(v: View?, event: DragEvent?): Boolean {
            // Handles each of the expected events
            when (event?.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    // Determines if this View can accept the dragged data
                    if (event?.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                          view.visibility=View.INVISIBLE
                        v?.invalidate()
                        true
                    } else {
                        false
                    }
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    true
                }
                DragEvent.ACTION_DRAG_LOCATION ->
                    true
                DragEvent.ACTION_DRAG_EXITED -> {
                    true
                }
                DragEvent.ACTION_DROP -> {
                    true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    view.sticker.possition = Point(0, 0)
                    v?.visibility = View.VISIBLE
                    // Turns off any color tinting
                    (v as? ImageView)?.clearColorFilter()

                    // Invalidates the view to force a redraw
                    v?.invalidate()

                    // Does a getResult(), and displays what happened.


                    // returns true; the value is ignored.
                    true
                }
                else -> {
                    // An unknown action type was received.
                    Log.e("DragDrop Example", "Unknown action type received by OnDragListener.")
                    false
                }
            }

        return true
    }
    }

    class StickerLongClickListener():View.OnLongClickListener
    {
        override fun onLongClick(v: View?): Boolean {
            val item = ClipData.Item(v?.tag as? CharSequence)
            val dragData = ClipData(
                v?.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item)
            val myShadow = StickerView.MyDragShadowBuilder(v!!)

            // Starts the drag
            v?.startDrag(
                dragData,   // the data to be dragged
                myShadow,   // the drag shadow builder
                null,       // no need to use local data
                0           // flags (not currently used, set to 0)
            )
            return true
        }
    }
}


