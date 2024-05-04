package com.example.randomapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomapplication.databinding.ActivityInfoBinding
import com.example.randomapplication.databinding.ActivityPage3Binding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val value = intent.getStringExtra("source")
        if (value.equals("car")){
            binding.kashish.setImageResource(R.drawable.baseline_back_hand_24)
        }
        else if (value.equals("bike")){
            binding.kashish.setImageResource(R.drawable.baseline_audiotrack_24)
        }
    }
}