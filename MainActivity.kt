package com.example.randomapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomapplication.databinding.ActivityMainBinding
import java.util.Collections
import java.util.Stack
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.click.setOnClickListener {
            val nextActivity = ActivityManager.getNextActivity(MainActivity::class.java)
            val intent = Intent(this, nextActivity)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        binding.radom.setOnClickListener {
            val nextActivity = ActivityManager.getNextActivity(MainActivity::class.java)
            val intent = Intent(this, nextActivity)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
    }

}