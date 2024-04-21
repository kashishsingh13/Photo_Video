package com.example.photosapplication

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.view.View
import android.widget.MediaController
import android.widget.SeekBar
import com.example.photosapplication.Model.Video
import com.example.photosapplication.databinding.ActivityDisplayBinding

class DisplayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDisplayBinding
    private var currentPosition: Int = 0

    private var videoList: List<Video>? = null
    private var mediaPlayer: MediaPlayer? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val videoList = intent.getParcelableArrayListExtra<Video>("video")
        val videoArray = intent.getParcelableArrayExtra("video")
        val videoList = videoArray?.map { it as Video } ?: emptyList()
        currentPosition = intent.getIntExtra("position", 0)

        // Set up MediaController for controlling video playback
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.imageView)
        binding.imageView.setMediaController(mediaController)
//        initializeVideoPlayer()


        // Set video URI
        binding.imageView.setVideoURI(Uri.parse(videoList!![currentPosition].path))
        binding.textView.text = videoList!![currentPosition].name
        binding.imageView.start()


        // Set onClickListener for play/pause button
        binding.pause.setOnClickListener {

                binding.imageView.pause()
                binding.play.visibility = View.VISIBLE
                binding.pause.visibility = View.GONE



        }
        binding.play.setOnClickListener {

                binding.imageView.start()
                binding.imageView.isPlaying
                binding.play.visibility = View.GONE
                binding.pause.visibility = View.VISIBLE

        }


//
//            } else {
//                binding.imageView.pause()
//                binding.play.visibility = View.VISIBLE
//                binding.pause.visibility = View.GONE
//
//            }
//        }


        // Set onClickListener for back button
        binding.back.setOnClickListener {
            if (currentPosition > 0) {
                currentPosition--
                binding.imageView.setVideoURI(Uri.parse(videoList[currentPosition].path))
                binding.imageView.start()
            }
        }


        binding.next.setOnClickListener {
            if (currentPosition < videoList!!.size - 1) {
                currentPosition++
                binding.imageView.setVideoURI(Uri.parse(videoList[currentPosition].path))
                binding.imageView.start()
            }
        }

    }
    private fun initializeVideoPlayer() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding.imageView)
        binding.imageView.setMediaController(mediaController)

        // Set video URI
        binding.imageView.setVideoURI(Uri.parse(videoList!![currentPosition].path))

        // Start playing the video
        binding.imageView.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()


        }
}