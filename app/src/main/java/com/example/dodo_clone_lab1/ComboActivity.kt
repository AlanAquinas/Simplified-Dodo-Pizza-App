package com.example.dodo_clone_lab1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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

        val title: TextView = findViewById(R.id.combo_activity_title)
        val sizeDesc: TextView = findViewById(R.id.combo_activity_size_desc)
        val description: TextView = findViewById(R.id.combo_activity_desc)
        val image: ImageView = findViewById(R.id.imageView)


        title.text = intent.getStringExtra("comboTitle")
        sizeDesc.text = intent.getStringExtra("sizeDesc")
        description.text = intent.getStringExtra("comboDesc")

        val imageId = intent.getIntExtra("imageId", 0)

        image.setImageResource(imageId)

        val floatingBackButton: FloatingActionButton = findViewById(R.id.floating_back_button1)

        floatingBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}