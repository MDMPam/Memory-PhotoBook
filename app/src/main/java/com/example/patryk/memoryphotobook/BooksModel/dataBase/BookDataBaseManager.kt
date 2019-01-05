package com.example.patryk.memoryphotobook.BooksModel.dataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val DataBaseName="BookBase"
const val version=1

const val bookTableName="bookTable"
const val book_ID="bookID"
const val book_defBackGroundColor="backColor"

const val pageTableName="pageTable"
const val page_bookID="bookID"
const val page_ID="pageID"
const val page_backgroundColor="backColor"

const val textTableName="textTable"
const val text_ID="textID"
const val text_pageID="pageID"
const val text_href="href"
const val text_text="text"
const val text_level="level"
const val text_color="color"
const val text_posX="posX"
const val text_posY="posY"

const val stickerTableName="stickerTable"
const val sticker_ID="stickerID"
const val sticker_pageID="pageID"
const val sticker_href="href"
const val sticker_bitmap="bitmap"
const val sticker_level="level"
const val sticker_filter="filter"
const val sticker_posX="posX"
const val sticker_posY="posY"
const val sticker_wight="wight"
const val sticker_height="height"

const val imageTableName="imageTable"
const val image_ID="imageID"
const val image_pageID="pageID"
const val image_href="href"
const val image_bitmap="bitmap"
const val image_level="level"
const val image_filter="filter"
const val image_posX="posX"
const val image_posY="posY"
const val image_wight="wight"
const val image_height="height"



class BookDataBaseManager(var context: Context):SQLiteOpenHelper(context, DataBaseName,null, version) {
    lateinit var db:SQLiteDatabase
    override fun onOpen(db: SQLiteDatabase) {
        this.db=db
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createBookTable=" CREATE TABLE $bookTableName (" +
                "$book_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$book_defBackGroundColor INTEGER " +
                ")"
        val createPageTable="CREATE TABLE $pageTableName ( " +
                "$page_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$page_bookID INTEGER, " +
                "$page_backgroundColor INTEGER" +
                ")"
        val createTextTable=" CREATE TABLE $textTableName (" +
                "$text_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$text_pageID INTEGER, " +
                "$text_text VARCHAR(256), " +
                "$text_href VARCHAR(256), " +
                "$text_level INTEGER, " +
                "$text_color INTEGER, " +
                "$text_posX INTEGER, " +
                "$text_posY INTEGER " +
                ")"
        val createStickerTable=" CREATE TABLE $stickerTableName (" +
                "$sticker_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$sticker_pageID INTEGER, " +
                "$sticker_href VARCHAR(256), " +
                "$sticker_level INTEGER, " +
                "$sticker_posX INTEGER, " +
                "$sticker_posY INTEGER " +
                ")"
        //toDo add path in sticker and add image
        
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}