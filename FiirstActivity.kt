package com.example.randomapplication

import android.app.Activity
import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.randomapplication.databinding.ActivityFiirstBinding
import java.util.Collections
import java.util.Random
import java.util.Stack

class FiirstActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFiirstBinding





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFiirstBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val arrowTextView = findViewById<TextView>(R.id.name)
        val arrowTextView1 = findViewById<ImageView>(R.id.back)


        arrowTextView.setText("Kashish singh")
        arrowTextView1.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()

        }


        binding.click.setOnClickListener {
            val nextActivity = com.example.randomapplication.ActivityManager.getNextActivity(FiirstActivity::class.java)
            val intent = Intent(this, nextActivity)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        binding.radom.setOnClickListener {
            val nextActivity = com.example.randomapplication.ActivityManager.getNextActivity(FiirstActivity::class.java)
            val intent = Intent(this, nextActivity)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
    }






}