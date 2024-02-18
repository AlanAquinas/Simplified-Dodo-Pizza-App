package com.example.dodo_clone_lab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PizzaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pizza)

        val title: TextView = findViewById(R.id.pizza_list_title)
        val sizeDesc: TextView = findViewById(R.id.pizza_list_size_desc)
        val description: TextView = findViewById(R.id.pizza_list_desc)


        title.text = intent.getStringExtra("pizzaTitle")
        sizeDesc.text = intent.getStringExtra("sizeDesc")
        description.text = intent.getStringExtra("pizzaDesc")

        val floatingBackButton: FloatingActionButton = findViewById(R.id.floating_back_button)

        floatingBackButton.setOnClickListener {
            onBackPressed()
        }
    }
}