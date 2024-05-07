package com.example.randomapplication

import android.app.Activity
import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomapplication.databinding.ActivityFourBinding
import com.example.randomapplication.databinding.ActivityThirdBinding
import java.util.Collections
import java.util.Random
import java.util.Stack

class FourActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFourBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFourBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.click.setOnClickListener {
            val nextActivity = com.example.randomapplication.ActivityManager.getNextActivity(FourActivity::class.java)
            val intent = Intent(this, nextActivity)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)


    }
        binding.radom.setOnClickListener {
            val nextActivity = com.example.randomapplication.ActivityManager.getNextActivity(FourActivity::class.java)
            val intent = Intent(this, nextActivity)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }


    }


}