package com.example.module7application.Que4

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ProgressBar
import com.example.module7application.Model.Music
import com.example.module7application.R
import com.example.module7application.databinding.ActivityPlaySongBinding
import com.squareup.picasso.Picasso
import java.util.Timer
import java.util.TimerTask

class PlaySongActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPlaySongBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var progressBar: ProgressBar
    private var isPlaying = false
    private var currentSongPosition: Int = 0
    private  var musicList= mutableListOf<Music>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPlaySongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var music = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("music", Music::class.java)
        } else {
            intent.getParcelableExtra("music")
        }
        if (music != null) {
            Picasso.get().load(music.image).into(binding.imageView)
            binding.textView.text = music.name

        }
        mediaPlayer = MediaPlayer()
        progressBar = binding.progressBar

        currentSongPosition = musicList.indexOf(music)

        setupMediaPlayer()
        setupClickListeners()

    }
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
    private fun showPlayImage() {
        binding.play.visibility = View.VISIBLE
        binding.stop.visibility = View.GONE
    }

    private fun showStopImage() {
        binding.play.visibility = View.GONE
        binding.stop.visibility = View.VISIBLE
    }
    private fun setupMediaPlayer() {
        val music = intent.getParcelableExtra<Music>("music")
        mediaPlayer.apply {
            setDataSource(music?.music)
            prepareAsync()
            setOnCompletionListener {
               this@PlaySongActivity.isPlaying =false
                showPlayImage()
                seekTo(0)
                progressBar.progress = 0
            }
            setOnPreparedListener {
                progressBar.max = duration
                progressBar.progress = 0

            }
        }
        // Update progress bar while playing
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    if (isPlaying) {
                        progressBar.progress = mediaPlayer.currentPosition
                    }
                }
            }
        }, 0, 1000)
        progressBar.setOnTouchListener { _, event ->
            onProgressBarClick(event)
            true
        }


    }
    private fun onProgressBarClick(event: MotionEvent) {
        val newPosition = (event.x / progressBar.width) * progressBar.max
            mediaPlayer.seekTo(newPosition.toInt())
            progressBar.progress = newPosition.toInt()

    }
    private fun setupClickListeners() {
        binding.play.setOnClickListener { onPlayButtonClick() }
        binding.stop.setOnClickListener { onStopButtonClick() }
        binding.next.setOnClickListener { onNextButtonClick() }

    }
    private fun onNextButtonClick() {
        // Increment the currentSongPosition to move to the next song
        currentSongPosition++

        // Check if there are more songs in the list
        if (currentSongPosition < musicList.size) {
            val nextSong = musicList[currentSongPosition]

            // Stop the current playback
            mediaPlayer.stop()

            // Set up the MediaPlayer for the next song
            mediaPlayer.reset()
            mediaPlayer.setDataSource(nextSong.music)
            mediaPlayer.prepareAsync()

            // Update UI with the details of the next song
            updateUI(nextSong)
        } else {
            // If there are no more songs, you can handle this according to your app's logic
            // For example, you can loop back to the first song or stop playback.
        }
    }
    private fun updateUI(music: Music) {
        // Update the UI with the details of the current song
        Picasso.get().load(music.image).into(binding.imageView)
        binding.textView.text = music.name

        // Start playing the next song
        mediaPlayer.start()

        // Update UI elements as needed (e.g., change button images)
        showStopImage()

        // Set isPlaying to true
        isPlaying = true
    }
    private fun onPlayButtonClick() {
        if (isPlaying) {
            mediaPlayer.pause()
            showPlayImage()
        } else {
            mediaPlayer.start()
            showStopImage()
        }
        isPlaying = !isPlaying
    }
    private fun onStopButtonClick() {
        mediaPlayer.pause()
        showPlayImage()
        isPlaying = false
    }

}