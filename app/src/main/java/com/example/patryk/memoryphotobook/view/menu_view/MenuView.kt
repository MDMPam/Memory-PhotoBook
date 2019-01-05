package com.example.patryk.memoryphotobook.view.menu_view

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.patryk.memoryphotobook.BooksModel.PageTemplate
import com.example.patryk.memoryphotobook.R
import com.example.patryk.memoryphotobook.view.EditView.EditBookView
import kotlinx.android.synthetic.main.activity_menu_view.*

class MenuView : AppCompatActivity() {

    companion object {
        const val bookTitle="title"
        const val bookTemplate="template"
        const val bookViewWidth="wight"
        const val bookViewHight="height"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_view)
        button_newBook.setOnClickListener { createNew() }
    }

    private fun createNew()
    {

        var titleInput: EditText = EditText(this).apply {
            this.hint=getString(R.string.createNewBookHint)
        }

        val builder: AlertDialog.Builder
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert)
        } else {
            builder = AlertDialog.Builder(this)
        }
        builder.setTitle(getString(R.string.creteNewBookTitle))
            .setView(titleInput)
            .setPositiveButton(android.R.string.yes, DialogInterface.OnClickListener { dialog, which ->
                var title=titleInput.text.toString()
                if(title.isEmpty()) title="Empty Title Book"
                var startIntent=Intent(this,EditBookView::class.java)
                startIntent.putExtra(bookTitle,title)
                startIntent.putExtra(bookTemplate,PageTemplate.None.value)
                startIntent.putExtra(bookViewHight,layout_main.height)
                startIntent.putExtra(bookViewWidth,layout_main.width)
                startActivity(startIntent)
            })
            .setNegativeButton(android.R.string.no, DialogInterface.OnClickListener { dialog, which ->
                // do nothing
            })
            .setIcon(android.R.drawable.ic_dialog_info)
            .show()
    }
}
