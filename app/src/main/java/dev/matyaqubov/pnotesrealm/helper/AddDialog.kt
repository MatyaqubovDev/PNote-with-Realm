package dev.matyaqubov.pnotesrealm.helper

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.EditText
import android.widget.TextView
import dev.matyaqubov.pnotesrealm.R
import dev.matyaqubov.pnotesrealm.activity.MainActivity
import dev.matyaqubov.pnotesrealm.manager.RealmManager
import dev.matyaqubov.pnotesrealm.model.Note
import java.text.SimpleDateFormat
import java.util.*

class AddDialog(a: Activity) : Dialog(a) {
    private lateinit var et_note: EditText
    private lateinit var tv_add: TextView
    private lateinit var tv_cancel: TextView
    private lateinit var date: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_add_note)
        initViews()
        setCancelable(true)
    }

    private fun initViews() {
        et_note = findViewById(R.id.et_note)
        tv_add = findViewById(R.id.tv_add)
        tv_cancel = findViewById(R.id.tv_cancel)
        tv_cancel.setOnClickListener {
            et_note.setText("")
            dismiss()
        }
        tv_add.setOnClickListener {
            val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:MM:SS")
            var date = simpleDateFormat.format(Date())
            val note = Note(date, et_note.text.toString())
            RealmManager.instance!!.saveNote(note)
            MainActivity.notes.add(note)
            //adaptetni yangilash
            MainActivity.adapter.notifyDataSetChanged()
            dismiss()
        }
    }
}