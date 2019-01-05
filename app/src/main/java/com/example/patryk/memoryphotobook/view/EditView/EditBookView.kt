package com.example.patryk.memoryphotobook.view.EditView

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import com.example.patryk.memoryphotobook.BooksModel.*
import com.example.patryk.memoryphotobook.DisplayPresenter
import com.example.patryk.memoryphotobook.R
import com.example.patryk.memoryphotobook.view.EditView.ImageView.RichImageView
import com.example.patryk.memoryphotobook.view.EditView.ImageView.StickerView
import com.example.patryk.memoryphotobook.view.EditView.ImageView.TextView
import kotlinx.android.synthetic.main.edit_book_view.*

import android.view.ScaleGestureDetector
import android.widget.ImageView
import android.view.MotionEvent
import android.widget.EditText
import com.example.patryk.memoryphotobook.view.EditView.ImageView.IObjectView
import com.example.patryk.memoryphotobook.view.ImageListView.*
import com.example.patryk.memoryphotobook.view.menu_view.MenuView
import java.lang.Exception


class EditBookView : AppCompatActivity(),DisplayView {

    override var avaiableFont: Array<Typeface> = arrayOf()
    var selected:Image?=null
        set(value){
            field=value
        }

    override var avalbleColor: Array<Int> = arrayOf()
        set(value) {field=value
        }
    override var backgroundColor: Int=0
        get() = field
        set(value) {
            field=value
            displayLayout?.setBackgroundColor(field)}
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
            return displayLayout.height}
        set(value) {}
    override var width: Int
        get() {
            return displayLayout.width
            //val display = windowManager.defaultDisplay
           // val size = Point()
            //display.getSize(size)
            //return  size.x
            }
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
        val template :PageTemplate? =try{PageTemplate.fromInt(intent.getIntExtra(MenuView.bookTemplate,0))}catch (e:Exception){null}
        val initWight :Int? =try{intent.getIntExtra(MenuView.bookViewWidth,0)-108}catch (e:Exception){null}
        val initHeight :Int? =try{intent.getIntExtra(MenuView.bookViewHight,0)}catch (e:Exception){null}
        val title :String =try{intent.getStringExtra(MenuView.bookTitle)}catch (e:Exception){""}

        elementToChoiceLayout =Layout_Images
        optionsLayout=constraintLayout_options
        optionsLayout.setOnDragListener(OptionLayoutDrag(this))
        displayLayout = findViewById<ConstraintLayout>(R.id.constraintLayot_display)
        //elementToChoiceLayout.setOnDragListener(ImageDisplayLayoutDrag(this))
        presenter= DisplayPresenter(this,title,template,initWight,initHeight)
        findViewById<Button>(R.id.button_addSticker).setOnClickListener {
           setStickerInView()
        }
        findViewById<Button>(R.id.button_frame).setOnClickListener {
            setFrameInView()
        }
        findViewById<Button>(R.id.button_addImage).setOnClickListener {
            setPhotoInView()
        }
        button_filter.setOnClickListener { setFilterInView() }
        button_color.setOnClickListener { setColorInView() }
        button_level.setOnClickListener { setLevelInView() }
        button_nextPage.setOnClickListener { presenter.nextPage() }
        button_prevPage.setOnClickListener {presenter.previousPage()        }
        button_text.setOnClickListener { setFontInView() }
        button_saveAsHtml.setOnClickListener { presenter.saveAsHtml() }
        button_PDF.setOnClickListener { presenter.saveAsPDF() }
        displayLayout.setOnDragListener(
            ImageDisplayLayoutDrag(
                this
            )
        )
        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
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
    fun setColorInView()
    {
        elementToChoiceLayout.removeAllViews()
        avalbleColor.forEach {
            val view = SingleColorElementList(this,it)
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
    fun setFontInView()
    {
        elementToChoiceLayout.removeAllViews()
        avaiableFont.forEach {
            val view = SingleFontElementList(this,it)
            elementToChoiceLayout.addView(view)
        }
    }
    fun setLevelInView()
    {
        elementToChoiceLayout.removeAllViews()
        for(i in 1 until 10)
        {
            val view = SingleLevelElementList(this,i)
            elementToChoiceLayout.addView(view)
        }
    }
    fun addText(typeface:Typeface,position:Point){
        var descriptionView:EditText= EditText(this).apply {
            this.hint="opis"
        }
        var hrefView=EditText(this).apply { hint="link do opisu" }
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(descriptionView)
        layout.addView(hrefView)


        val builder: AlertDialog.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(context)
        }
        builder.setTitle("Dodaj opis")
            //.setMessage("Are you sure you want to delete this entry?")
            .setView(layout)
            //.setView(hrefView)
            .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, which ->
                if(descriptionView.text.toString().isNotEmpty())
                    presenter.setHref(presenter.setTypeface(presenter.move(presenter.addText(descriptionView.text.toString()),position),typeface),hrefView.text.toString())
            })
            .setNegativeButton(android.R.string.no, DialogInterface.OnClickListener { dialog, which ->
                // do nothing
            })
            .setIcon(android.R.drawable.ic_dialog_info)
            .show()

        var x=0

    }

    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {

        mScaleGestureDetector?.onTouchEvent(motionEvent)
        return true
    }

    private var mScaleGestureDetector: ScaleGestureDetector? = null
    private var mScaleFactor = 1.0f
    private var mImageView: ImageView? = null
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = Math.max(
                0.1f,
                Math.min(mScaleFactor, 2.0f)
            )
            for (i in 0 until  displayLayout.childCount)
            {
                try{
                    val obj=displayLayout.getChildAt(i) as IObjectView
                    if((obj).image==selected)
                        obj.reSize(mScaleFactor)
                }catch (e:Exception){}
            }
            return true
        }
    }

}