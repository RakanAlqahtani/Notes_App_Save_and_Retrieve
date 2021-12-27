package com.example.notesappsaveandretrieve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var notes : ArrayList<Note>
    private lateinit var adapter : RVAdapter
    private val databaseHelper by lazy { DatabaseHelper(applicationContext) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notes = arrayListOf()
        adapter = RVAdapter(notes)
        rvMain.adapter = adapter
        rvMain.layoutManager = LinearLayoutManager(this)

        btSave.setOnClickListener {
            if(etNote.text.toString().isNotEmpty()) {
                val note = etNote.text.toString()
                databaseHelper.addNote(note)
                Toast.makeText(this, "Added successfully", Toast.LENGTH_LONG).show()
                notes = databaseHelper.readData()
                adapter.update(notes)
            }
            notes = databaseHelper.readData()
            adapter.update(notes)

        }


    }
}