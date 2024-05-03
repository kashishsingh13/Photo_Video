package com.exmple.foodzone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.exmple.foodzone.Preference.PrefManager
import com.exmple.foodzone.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                var manager = PrefManager(this)
                if (manager.getLoginStatus()) {
                    // true - already login
                    var intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {

                    if (manager.getOnBoardingStatus()) {
                        // true
                        var intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        // false
                        var intent = Intent(this, OnBoardingActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

            },3000

        )
    }
}