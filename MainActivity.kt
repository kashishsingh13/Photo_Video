package com.example.randomapplication

import android.app.Activity
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Toast
import android.Manifest
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.randomapplication.databinding.ActivityMainBinding
import java.util.Collections
import java.util.Stack
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val CALL_PERMISSION_REQUEST_CODE = 101
    private var callReceiver: CallReceiver? = null





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        registerBootReceiver()


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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.PROCESS_OUTGOING_CALLS
                ),
                CALL_PERMISSION_REQUEST_CODE
            )
        } else {
            registerCallReceiver()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                registerCallReceiver()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerCallReceiver() {
        callReceiver = CallReceiver()
        val intentFilter = IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED)
        registerReceiver(callReceiver, intentFilter)
    }
    private fun registerBootReceiver() {
        val filter = IntentFilter(Intent.ACTION_BOOT_COMPLETED)
        registerReceiver(BootReceiver(), filter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(callReceiver)

    }

}