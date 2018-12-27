package com.example.patryk.memoryphotobook.view.EditView

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.ClipDescription
import android.content.Context
import android.content.DialogInterface
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
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import com.example.patryk.memoryphotobook.BooksModel.*
import com.example.patryk.memoryphotobook.DisplayPresenter
import com.example.patryk.memoryphotobook.R
import com.example.patryk.memoryphotobook.view.ImageListView.SingleFilterListElement
import com.example.patryk.memoryphotobook.view.ImageListView.SingleFrameListElement
import com.example.patryk.memoryphotobook.view.ImageListView.SinglePhotoListElement
import com.example.patryk.memoryphotobook.view.ImageListView.SingleStickerListElement
import com.example.patryk.memoryphotobook.view.RichImageView
import com.example.patryk.memoryphotobook.view.StickerView
import com.example.patryk.memoryphotobook.view.TextView
import kotlinx.android.synthetic.main.edit_book_view.*
import java.lang.Exception

class EditBookView : AppCompatActivity(),DisplayView {
    override var avalblePhotoLst: Array<Bitmap> = arrayOf()
    override var availableSticker: Array<Bitmap> = arrayOf()


    override var avalibleFilterr: Array<CFilter> = arrayOf()
    override var imageList: Array<RichImage> = arrayOf()
        set(value) {
            field=value
            displayLayout.removeAllViews()
            drawAllList()
        }
    override var avalibleFrame: Array<Frame> = arrayOf()

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
            var view = TextView(this, it)
            // view.setOnTouchListener(MyTouchListener())
            displayLayout.addView(view)

        }
        stickerList.forEach {
            var view = StickerView(this, it)
            //view.setOnTouchListener(MyTouchListener())
            displayLayout.addView(view)
        }

        imageList.forEach {
            var view = RichImageView(this, it)
            // view.setOnTouchListener(MyTouchListener())
            displayLayout.addView(view)
        }
    }

    var defaultImage:Bitmap
        get() = avalblePhotoLst[0]
        set(value) {}
    lateinit var displayLayout:ConstraintLayout
    lateinit var elementToChoiceLayout: LinearLayout
    lateinit var presenter:DisplayPresenter
    lateinit var optionsLayout:ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_book_view)
        elementToChoiceLayout =Layout_Images
        optionsLayout=constraintLayout_options
        optionsLayout.setOnDragListener(OptionLayoutDrag(this))
        //elementToChoiceLayout.setOnDragListener(ImageDisplayLayoutDrag(this))
        presenter= DisplayPresenter(this,"title")
        findViewById<Button>(R.id.button_addSticker).setOnClickListener {
           setStickerInView()
        }
        findViewById<Button>(R.id.button_Text).setOnClickListener {
            setFrameInView()
        }
        findViewById<Button>(R.id.button_addImage).setOnClickListener {
            setPhotoInView()
        }
        button_filter.setOnClickListener { setFilterInView() }

        displayLayout = findViewById<ConstraintLayout>(R.id.constraintLayot_display)
        displayLayout.setOnDragListener(
            ImageDisplayLayoutDrag(
                this
            )
        )
    }
    fun setFrameInView()
    {
        elementToChoiceLayout.removeAllViews()
        avalibleFrame.forEach {
            val view = SingleFrameListElement(this,it)
            elementToChoiceLayout.addView(view)
        }
    }
    fun setFilterInView()
    {
        elementToChoiceLayout.removeAllViews()
        avalibleFilterr.forEach {
            val view = SingleFilterListElement(this,it)
            elementToChoiceLayout.addView(view)
        }
    }
    fun setPhotoInView()
    {
        elementToChoiceLayout.removeAllViews()
        avalblePhotoLst.forEach {
            val view = SinglePhotoListElement(this,it)
            elementToChoiceLayout.addView(view)
        }
    }
    fun setStickerInView(){
        elementToChoiceLayout.removeAllViews()
        availableSticker.forEach {
            val view = SingleStickerListElement(this,it)
            elementToChoiceLayout.addView(view)
        }
    }



}