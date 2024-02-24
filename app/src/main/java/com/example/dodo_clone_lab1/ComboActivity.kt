package com.example.dodo_clone_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ComboActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_combo)
//
//        val title: TextView = findViewById(R.id.pizza_list_title)
//        val description: TextView = findViewById(R.id.pizza_list_desc)
//
//
//        title.text = intent.getStringExtra("pizzaTitle")
//        description.text = intent.getStringExtra("pizzaDesc")
//
//        val floatingBackButton: FloatingActionButton = findViewById(R.id.floating_back_button)
//
//        floatingBackButton.setOnClickListener {
//            onBackPressed()
//        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combo)

        val title: TextView = findViewById(R.id.pizza_list_title)
        val description: TextView = findViewById(R.id.pizza_list_desc)


        title.text = intent.getStringExtra("pizzaTitle")
        description.text = intent.getStringExtra("pizzaDesc")

        val floatingBackButton: FloatingActionButton = findViewById(R.id.floating_back_button)

        floatingBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}