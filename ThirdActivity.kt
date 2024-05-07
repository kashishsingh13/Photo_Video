package com.example.randomapplication

import android.app.Activity
import android.app.ActivityManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomapplication.databinding.ActivitySecondBinding
import com.example.randomapplication.databinding.ActivityThirdBinding
import java.util.Collections
import java.util.Random
import java.util.Stack

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.click.setOnClickListener {
            val nextActivity = com.example.randomapplication.ActivityManager.getNextActivity(ThirdActivity::class.java)
            val intent = Intent(this, nextActivity)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }
        binding.radom.setOnClickListener {
            val nextActivity = com.example.randomapplication.ActivityManager.getNextActivity(ThirdActivity::class.java)
            val intent = Intent(this, nextActivity)
            intent.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT
            startActivity(intent)
        }

    }


}