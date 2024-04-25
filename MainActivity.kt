package com.example.module7application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module7application.Adapter.QueAdapter
import com.example.module7application.Model.Que
import com.example.module7application.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var mAdapter: QueAdapter
    private var itemlist= mutableListOf<Que>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prepareData()

           mAdapter= QueAdapter(this,itemlist)
           binding.recycle.layoutManager=LinearLayoutManager(this)
            binding.recycle.adapter=mAdapter


    }
    private fun prepareData(){
        itemlist.add(
         Que(1,"1.set animation on splash screen with app logo")
        )
        itemlist.add(
            Que(2,"2.Create an application to play song from raw resource folder")
        )
        itemlist.add(
            Que(3,"3.Create an application to play song from mobile memory")
        )
        itemlist.add(
            Que(4,"4.Create an application to play song from Server")
        )
        itemlist.add(
            Que(5,"5.use WAKE LOCK when playing video play")
        )
        itemlist.add(
            Que(6,"6.Create an application to convert text typed in edit text into speech")
        )
        itemlist.add(
            Que(7,"7.Create an application for Wi-Fi on-off")
        )

    }
}


//// Define your notification channel ID
//val CHANNEL_ID = "your_channel_id"
//
//// Create a unique notification ID
//val NOTIFICATION_ID = 1
//
//// Create a NotificationManagerCompat instance
//val notificationManager = NotificationManagerCompat.from(context)
//
//// Create a RemoteViews instance for your custom notification layout
//val remoteViews = RemoteViews(context.packageName, R.layout.custom_notification_layout)
//
//// Initialize the play/pause button in the custom notification layout
//val playPauseButton = remoteViews.setOnClickPendingIntent(R.id.play_pause_button, getPendingIntent())
//
//// Create a NotificationCompat.Builder instance
//val builder = NotificationCompat.Builder(context, CHANNEL_ID)
//    .setSmallIcon(R.drawable.notification_icon)
//    .setContent(remoteViews)
//    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//// Define a function to toggle between play and pause icons
//fun togglePlayPause() {
//    // Determine whether the media is playing or paused
//    val isPlaying = // Add your logic here to determine if media is playing or paused
//
//    // Update the play/pause button icon based on the media state
//    val iconResource = if (isPlaying) R.drawable.pause_icon else R.drawable.play_icon
//    remoteViews.setImageViewResource(R.id.play_pause_button, iconResource)
//
//    // Update the notification
//    notificationManager.notify(NOTIFICATION_ID, builder.build())
//}
//
//// Define a PendingIntent for the play/pause button
//fun getPendingIntent(): PendingIntent {
//    // Define an Intent for the broadcast receiver
//    val intent = Intent(context, YourBroadcastReceiver::class.java).apply {
//        action = "your_action"
//    }
//
//    // Create a PendingIntent for the broadcast receiver
//    return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//}

// In your foreground service

//class MusicService : Service() {
//    private var isPlaying = false
//
//    // Method to start playing music
//    fun startPlayback() {
//        // Start playback logic
//        isPlaying = true
//        updateNotification()
//    }
//
//    // Method to pause music playback
//    fun pausePlayback() {
//        // Pause playback logic
//        isPlaying = false
//        updateNotification()
//    }
//
//    // Method to stop music playback
//    fun stopPlayback() {
//        // Stop playback logic
//        isPlaying = false
//        stopForeground(true)
//    }
//
//    // Method to update the notification based on playback state
//    private fun updateNotification() {
//        val iconResource = if (isPlaying) R.drawable.pause_icon else R.drawable.play_icon
//        remoteViews.setImageViewResource(R.id.play_pause_button, iconResource)
//        notificationManager.notify(NOTIFICATION_ID, builder.build())
//    }
//
//    // Other service methods...
//}
//
//// In your music activity
//
//class MusicActivity : AppCompatActivity() {
//    private lateinit var musicService: MusicService
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        // Initialize your music service
//        musicService = MusicService()
//
//        // Start foreground service
//        startForegroundService(Intent(this, MusicService::class.java))
//    }
//
//    // Method to handle play/pause button click
//    fun onPlayPauseClicked() {
//        if (musicService.isPlaying) {
//            // Pause music playback
//            musicService.pausePlayback()
//        } else {
//            // Start music playback
//            musicService.startPlayback()
//        }
//    }
//
//    // Other activity methods...
//}

