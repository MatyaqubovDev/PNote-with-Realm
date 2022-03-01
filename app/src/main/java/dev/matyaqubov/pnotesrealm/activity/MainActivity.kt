package dev.matyaqubov.pnotesrealm.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.matyaqubov.pnotesrealm.MyApplication
import dev.matyaqubov.pnotesrealm.R
import dev.matyaqubov.pnotesrealm.adapter.NoteAdapter
import dev.matyaqubov.pnotesrealm.helper.AddDialog
import dev.matyaqubov.pnotesrealm.manager.RealmManager

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView

    companion object {
        var notes = MyApplication.notes
        var adapter = NoteAdapter(notes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 1)
        recyclerView.adapter = adapter
        val add = findViewById<FloatingActionButton>(R.id.b_add_note)
        add.setOnClickListener {
            val dialog = AddDialog(this)
            dialog.show()
        }
    }

}