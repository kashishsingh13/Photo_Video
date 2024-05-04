package com.example.randomapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomapplication.databinding.ActivityPage1Binding
import com.example.randomapplication.databinding.ActivityPage2Binding

class page2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityPage2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPage2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val value = intent.getStringExtra("source")

        binding.car.setOnClickListener {
            startActivity(Intent(this,PageActivity3::class.java).putExtra("source", value))
        }
    }
}