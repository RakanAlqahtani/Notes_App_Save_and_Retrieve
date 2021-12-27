package com.example.notesappsaveandretrieve

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,
    DATABASE_VERSION) {

    companion object {
        private  var DATABASE_VERSION = 1
        private const val DATABASE_NAME = "notes.db"
        private const val ID = "id"

        private const val NOTE = "note"
    }


   private val sqLiteDatabase : SQLiteDatabase = writableDatabase
    override fun onCreate(db: SQLiteDatabase?) {
        if(db != null) {
            db.execSQL("create table note ($DATABASE_VERSION INTGER PRIMARY KEY  ,Note Text)")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS note")
        onCreate(db)
    }

    fun addNote(note: String){
        val contentValue = ContentValues()

        contentValue.put("note",note)
        sqLiteDatabase.insert("note",null,contentValue)

    }

    fun readData(): ArrayList<Note>{
        val notes = arrayListOf<Note>()

        // read all data using cursor

        val cursor : Cursor = sqLiteDatabase.rawQuery("SELECT * FROM note",null)

        if (cursor.count < 1){
            println("No data Found")
        }else{
            while (cursor.moveToNext()){
                val pk = cursor.getInt(0)
                val note = cursor.getString(0)
                notes.add(Note(pk,note))
            }
        }


        return notes

    }
}