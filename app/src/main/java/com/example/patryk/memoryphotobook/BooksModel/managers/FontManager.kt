package com.example.patryk.memoryphotobook.BooksModel.managers

import android.content.Context
import android.graphics.Typeface

class FontManager(var context: Context) {
    var fontArray:Array<Typeface>
    init {
        fontArray= arrayOf(Typeface.createFromAsset(this.context.getAssets(),"Pacifico.ttf"),
            Typeface.createFromAsset(this.context.getAssets(),"Amatic-Bold.ttf"),
            Typeface.createFromAsset(this.context.getAssets(),"AmaticSC-Regular.ttf"),
            Typeface.createFromAsset(this.context.getAssets(),"FFF_Tusj.ttf"),
            Typeface.createFromAsset(this.context.getAssets(),"GreatVibes-Regular.otf")

            )
    }
}