package com.example.patryk.memoryphotobook

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.view.View
import android.widget.Button
import com.example.patryk.memoryphotobook.BooksModel.DisplayView

import com.example.patryk.memoryphotobook.BooksModel.Image
import com.example.patryk.memoryphotobook.BooksModel.Sticker
import com.example.patryk.memoryphotobook.view.StickerView
import android.view.MotionEvent

import com.example.patryk.memoryphotobook.BooksModel.Text
import com.example.patryk.memoryphotobook.view.TextView


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
                var view =StickerView(this,it)
                //view.setOnTouchListener(MyTouchListener())
                rl.addView(view)
            }
          //  setContentView(rl)
           // rl.invalidate()
        }
    override var textList: Array<Text> = arrayOf()
        set(value) {
            field=value
            val rl = findViewById<ConstraintLayout>(R.id.mainLayout)
            value.forEach {
                var view = TextView(this,it)
               // view.setOnTouchListener(MyTouchListener())
                rl.addView(view)
            }
        }
    lateinit var presenter:DisplayPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter= DisplayPresenter(this,"title")
        findViewById<Button>(R.id.button_createSticker).setOnClickListener {
            presenter.move(presenter.addSticker(availableSticker[0]),Point(500,50))
        }
        findViewById<Button>(R.id.button_createText).setOnClickListener {
            presenter.move(presenter.addText("ZÓŁĆ"),Point(50,50))
        }
    }


    class MyTouchListener() : View.OnTouchListener {
        var dX: Float = 0.toFloat()
        var dY:Float = 0.toFloat()
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            val X = motionEvent.getRawX().toInt()
            val Y = motionEvent.getRawY().toInt()
            when (motionEvent.getAction() and MotionEvent.ACTION_MASK) {
                MotionEvent.ACTION_DOWN -> {
                    dX = view.getX() - motionEvent.getRawX();
                    dY = view.getY() - motionEvent.getRawY();
                }
                MotionEvent.ACTION_UP -> {
                }
                MotionEvent.ACTION_POINTER_DOWN -> {
                }
                MotionEvent.ACTION_POINTER_UP -> {
                }
                MotionEvent.ACTION_MOVE -> {
                    view.animate()
                        .x(motionEvent.getRawX() + dX)
                        .y(motionEvent.getRawY() + dY)
                        .setDuration(0)
                        .start();
                }
            }
            //_root.invalidate()
            return false

        }
    }
}
