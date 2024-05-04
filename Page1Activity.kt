package com.example.randomapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.randomapplication.databinding.ActivityPage1Binding

class Page1Activity : AppCompatActivity() {
    private lateinit var binding:ActivityPage1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPage1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.car.setOnClickListener {
            startActivity(Intent(this,page2Activity::class.java).putExtra("source", "car"))
        }
        binding.bike.setOnClickListener {
            startActivity(Intent(this,page2Activity::class.java).putExtra("source", "bike"))
        }
        binding.share.setOnClickListener {
            val appPackageName = packageName
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Check out this cool app: https://play.google.com/store/apps/details?id=$appPackageName")
            startActivity(Intent.createChooser(intent, "Share via"))
        }
        binding.rating.setOnClickListener {
            val appPackageName = packageName
            val uri = Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent)

        }

    }
}

//private fun openPlayStoreForRating() {
//    val appPackageName = packageName
//    try {
//        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")))
//    } catch (e: android.content.ActivityNotFoundException) {
//        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
//    }
//}