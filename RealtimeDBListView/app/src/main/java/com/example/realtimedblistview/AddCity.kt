package com.example.realtimedblistview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_city.*

class AddCity : AppCompatActivity() {

    private lateinit var db: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_city)

        db = FirebaseDatabase.getInstance()

        val array = arrayListOf<Any>()




        addBtn.setOnClickListener {
            array.add(editText.text.toString())
            editText.setText("")
        }

        finishBtn.setOnClickListener {
            val cities = db.getReference()

            for (i in array){
                val key = cities.child("cities").push().key
                cities.child("cities").child(key!!).setValue(i)
            }


            val intent  = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}
