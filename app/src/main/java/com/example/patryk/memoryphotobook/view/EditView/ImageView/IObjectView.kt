package com.example.patryk.memoryphotobook.view.EditView.ImageView

import com.example.patryk.memoryphotobook.BooksModel.Image

interface IObjectView {
    var highlighted:Boolean
    var image: Image
    fun reSize(scale:Float)
}