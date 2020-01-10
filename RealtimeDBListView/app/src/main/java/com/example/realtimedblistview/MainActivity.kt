package com.example.realtimedblistview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView


class MainActivity : AppCompatActivity() {

    lateinit var database: FirebaseDatabase

    var array = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseDatabase.getInstance().getReference("cities")

        val adapter = ArrayAdapter<String>(this, R.layout.row_ui, arrayListOf())
        val listView: ListView = findViewById(R.id.listview_1)
        listView.setAdapter(adapter)


        db.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var values = dataSnapshot.getValue() as HashMap<*,*>


                for (value in values.values) {
//                    array.add(value.toString())
                    adapter.add(value.toString())
//                    textview.text = value.javaClass.toString()
                    textview.text = array.joinToString()
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
            }
        })





    }


}