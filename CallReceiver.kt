package com.example.randomapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            val state = intent?.getStringExtra(TelephonyManager.EXTRA_STATE)
            val phoneNumber = intent?.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)

            when (state) {
                TelephonyManager.EXTRA_STATE_RINGING -> {
                    // Incoming call
//                    Log.d("CallReceiver", "Incoming call from $phoneNumber")
//                    showToast(context, "Incoming call from $phoneNumber")
                    if (!phoneNumber.isNullOrEmpty()) {
                        Log.d("CallReceiver", "Incoming call from $phoneNumber")
                        showToast(context, "Incoming call from $phoneNumber")
                    } else {
                        Log.d("CallReceiver", "Incoming call with no caller ID")
                        showToast(context, "Incoming call with no caller ID")
                    }

                }
                TelephonyManager.EXTRA_STATE_OFFHOOK -> {
                    // Call answered or outgoing call
//                    Log.d("CallReceiver", "Call answered or outgoing call to $phoneNumber")
//                    showToast(context, "Call answered or outgoing call to $phoneNumber")
                    if (!phoneNumber.isNullOrEmpty()) {
                        Log.d("CallReceiver", "Call answered or outgoing call to $phoneNumber")
                        showToast(context, "Call answered or outgoing call to $phoneNumber")
                    } else {
                        Log.d("CallReceiver", "Outgoing call with no callee ID")
                        showToast(context, "Outgoing call with no callee ID")
                    }
                }
                TelephonyManager.EXTRA_STATE_IDLE -> {
                    // Call ended
                    Log.d("CallReceiver", "Call ended")
                    showToast(context, "Call ended")
                }
            }
        }
    }
    private fun showToast(context: Context?, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}