package com.example.patryk.memoryphotobook.view

import android.content.ClipDescription
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.Point
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import com.example.patryk.memoryphotobook.BooksModel.*
import com.example.patryk.memoryphotobook.DisplayPresenter
import com.example.patryk.memoryphotobook.R
import com.example.patryk.memoryphotobook.view.ImageListView.SingleFrameListElement
import kotlinx.android.synthetic.main.edit_book_view.*
import java.lang.Exception

class EditBookView : AppCompatActivity(),DisplayView {

    override var availableSticker: Array<Bitmap> = arrayOf()

    override var imageList: Array<RichImage> = arrayOf()
        set(value) {
            field=value
            displayLayout.removeAllViews()
            drawAllList()
        }
    override var frameList: Array<Frame> = arrayOf()
    set(value) {field=value
    elementToChoiceLayout.removeAllViews()
        value.forEach {
            val view = SingleFrameListElement(this,it)
            elementToChoiceLayout.addView(view)
        }
    }


    override var context: Context
        get() = this
        set(value) {}
    override var height: Int
        get() {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return  size.y}
        set(value) {}
    override var width: Int
        get() {
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            return  size.x}
        set(value) {}
    override var stickerList: Array<Sticker> = arrayOf()
        set(value) {
            field=value
            displayLayout.removeAllViews()
            drawAllList()
        }
    override var textList: Array<Text> = arrayOf()
        set(value) {
            field=value
            displayLayout.removeAllViews()
            drawAllList()
        }
    fun drawAllList()
    {
        textList.forEach {
            var view = TextView(this,it)
            // view.setOnTouchListener(MyTouchListener())
            displayLayout.addView(view)

        }
        stickerList.forEach {
            var view =StickerView(this,it)
            //view.setOnTouchListener(MyTouchListener())
            displayLayout.addView(view)
        }

        imageList.forEach {
            var view = RichImageView(this, it)
            // view.setOnTouchListener(MyTouchListener())
            displayLayout.addView(view)
        }
    }
    lateinit var displayLayout:ConstraintLayout
    lateinit var elementToChoiceLayout: LinearLayout
    lateinit var presenter:DisplayPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_book_view)
        elementToChoiceLayout =Layout_Images
        //elementToChoiceLayout.setOnDragListener(MyDrag(this))
        presenter= DisplayPresenter(this,"title")
        findViewById<Button>(R.id.button_addSticker).setOnClickListener {
            presenter.move(presenter.addSticker(availableSticker[0]), Point(50,50))
        }
        findViewById<Button>(R.id.button_Text).setOnClickListener {
            presenter.move(presenter.addText("ZÓŁĆ"), Point(50,50))
        }
        findViewById<Button>(R.id.button_addImage).setOnClickListener {
            presenter.setframe(presenter.move(presenter.addRichImage(availableSticker[0]), Point(50,0)),frameList[0])
        }
        displayLayout = findViewById<ConstraintLayout>(R.id.constraintLayot_display)
        displayLayout.setOnDragListener(MyDrag(this))

    }

    class  MyDrag(var view:EditBookView): View.OnDragListener
    {
        override fun onDrag(v: View, event: DragEvent): Boolean {
            // Defines a variable to store the action type for the incoming event
            val action = event.action
            // Handles each of the expected events
            when (action) {

                DragEvent.ACTION_DRAG_STARTED -> {
                    // Determines if this View can accept the dragged data
                    return if (event.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        // if you want to apply color when drag started to your view you can uncomment below lines
                        // to give any color tint to the View to indicate that it can accept data.
                        // v.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                        // Invalidate the view to force a redraw in the new tint
                        //  v.invalidate();
                        // returns true to indicate that the View can accept the dragged data.
                        true
                    } else false
                    // Returns false. During the current drag and drop operation, this View will
                    // not receive events again until ACTION_DRAG_ENDED is sent.
                }

                DragEvent.ACTION_DRAG_ENTERED -> {
                    // Applies a GRAY or any color tint to the View. Return true; the return value is ignored.
                    v?.background?.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN)
                    // Invalidate the view to force a redraw in the new tint
                    v?.invalidate()
                    return true
                }

                DragEvent.ACTION_DRAG_LOCATION ->
                    // Ignore the event
                    return true

                DragEvent.ACTION_DRAG_EXITED -> {
                    // Re-sets the color tint to blue. Returns true; the return value is ignored.
                    // view.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_IN);
                    //It will clear a color filter .
                    v?.background?.clearColorFilter()
                    // Invalidate the view to force a redraw in the new tint
                    v?.invalidate()
                    return true
                }

                DragEvent.ACTION_DROP -> {
                    // Gets the item containing the dragged data
                    val item = event.clipData.getItemAt(0)
                    // Gets the text data from the item.
                    val dragData = item.text.toString()
                    // Displays a message containing the dragged data.
                    // Toast.makeText(this, "Dragged data is $dragData", Toast.LENGTH_SHORT).show()
                    // Turns off any color tints
                    v?.background?.clearColorFilter()
                    // Invalidates the view to force a redraw
                    v?.invalidate()

                    val vw = event.localState as SingleFrameListElement
                    val owner = vw.parent as ViewGroup
                   // owner.removeView(vw) //remove the dragged view
                    //caste the view into LinearLayout as our drag acceptable layout is LinearLayout
                    //val container = v as ConstraintLayout
                    try {
                        event.x
                        view.presenter.move(view.presenter.setframe(view.presenter.addRichImage(view.availableSticker[0]),vw.frame),
                            Point(event.x.toInt()-vw.frame.bitMap.width/2,event.y.toInt()-vw.frame.bitMap.height/2)
                        )
                        }catch (e:Exception)
                    {
                        var x=0
                    }
                    vw?.visibility = View.VISIBLE//finally set Visibility to VISIBLE
                    // Returns true. DragEvent.getResult() will return true.
                    return true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    // Turns off any color tinting
                    v?.background?.clearColorFilter()
                    // Invalidates the view to force a redraw
                    v?.invalidate()
                    // Does a getResult(), and displays what happened.
                    if (event.result){}
                    //Toast.makeText(, "The drop was handled.", Toast.LENGTH_SHORT).show()
                    else{}
                    // Toast.makeText(this, "The drop didn't work.", Toast.LENGTH_SHORT).show()
                    // returns true; the value is ignored.
                        return true
                }
                // An unknown action type was received.
                else -> Log.e("DragDrop Example", "Unknown action type received by OnDragListener.")
            }
            return false
        }
    }
}