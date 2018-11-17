package com.example.patryk.memoryphotobook

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.text.Layout
import android.view.View
import android.widget.Button
import com.example.patryk.memoryphotobook.BooksModel.DisplayView

import com.example.patryk.memoryphotobook.BooksModel.Image
import com.example.patryk.memoryphotobook.BooksModel.Sticker
import android.widget.RelativeLayout
import com.example.patryk.memoryphotobook.view.DisplayedSticker


class MainActivity : AppCompatActivity(),DisplayView {

    override var availableSticker: Array<Bitmap> = arrayOf()

    override var imageList: Array<Image> = arrayOf()
       // get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}


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
            val rl = findViewById<ConstraintLayout>(R.id.mainLayout)
            value.forEach {
                rl.addView(DisplayedSticker(this,it))
            }
          //  setContentView(rl)
           // rl.invalidate()
        }
    lateinit var presenter:DisplayPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter= DisplayPresenter(this,"title")
        findViewById<Button>(R.id.button_createSticker).setOnClickListener {
            presenter.addSticker(availableSticker[0])
        }
    }
    fun refresh()
    {

    }
}
