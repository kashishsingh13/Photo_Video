package com.example.randomapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomapplication.databinding.ActivityPage2Binding
import com.example.randomapplication.databinding.ActivityPage3Binding

class PageActivity3 : AppCompatActivity() {
    private lateinit var binding: ActivityPage3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPage3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val value = intent.getStringExtra("source")

        binding.car.setOnClickListener {
            startActivity(Intent(this,InfoActivity::class.java).putExtra("source", value))
        }

    }
}