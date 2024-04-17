package com.example.module7application.Que4

import android.content.Intent


import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.module7application.Adapter.MusicAdapter
import com.example.module7application.Model.Music
import com.example.module7application.R
import com.example.module7application.databinding.ActivityPlaySongServerBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import java.io.IOException

class PlaySongServerActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPlaySongServerBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mRef: DatabaseReference
    lateinit var mAdapter: MusicAdapter
    private var musicList = mutableListOf<Music>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityPlaySongServerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        registerForContextMenu(binding.recycle)

        mAdapter = MusicAdapter(this, musicList)
        binding.recycle.layoutManager = LinearLayoutManager(this)
        binding.recycle.adapter = mAdapter


//        val songUrl = "https://firebasestorage.googleapis.com/v0/b/music-5d098.appspot.com/o/uploads%2F1703864984057_acc%3D1%3Bdoc%3Dencoded%3D7DWbm2P7oif0bb-vlonzUPyBt7xNptYLJNWBo-bLwTAEWq6mvgQ-2h1zWYE%3D?alt=media&token=44bc4075-88e5-44ee-ab99-90698871cd5f"
//        mediaPlayer = MediaPlayer()
//        mediaPlayer.setDataSource(songUrl)
//        mediaPlayer.prepareAsync()
//
//
//            mediaPlayer.setOnPreparedListener {
//                // Set up the play button click listener here after the media player is prepared
//                binding.btnPlay.setOnClickListener {
//                    if (!mediaPlayer.isPlaying) {
//                        mediaPlayer.start()
//                        binding.btnPlay.text = "Pause"
//                    } else {
//                        mediaPlayer.pause()
//                        binding.btnPlay.text = "Play"
//                    }
//                }
//            }

        mRef = Firebase.database.reference

        mRef.child("music-node").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
//                val newCategoryList = mutableListOf<Category>()
                musicList.clear()
                for(snap in snapshot.children){
                    var music = snap.getValue(Music::class.java)
                   music?.let { musicList.add(it) }
                }

//                categoryList.clear()
//                categoryList.addAll(newCategoryList)


                mAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

//

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.music_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add -> {
                startActivity(Intent(this,AddSongActivity::class.java))

                true
            }

            R.id.play -> {
//                val songUrl = "https://firebasestorage.googleapis.com/v0/b/music-5d098.appspot.com/o/uploads%2F1703864984057_acc%3D1%3Bdoc%3Dencoded%3D7DWbm2P7oif0bb-vlonzUPyBt7xNptYLJNWBo-bLwTAEWq6mvgQ-2h1zWYE%3D?alt=media&token=44bc4075-88e5-44ee-ab99-90698871cd5f"
//                mediaPlayer = MediaPlayer()
//                mediaPlayer.setDataSource(songUrl)
//                mediaPlayer.prepareAsync()
//
//
//                mediaPlayer.setOnPreparedListener {
//                    // Set up the play button click listener here after the media player is prepared
//                    R.id.play.setOnClickListener {
//                        if (!mediaPlayer.isPlaying) {
//                            mediaPlayer.start()
//                            binding.btnPlay.text = "Pause"
//                        } else {
//                            mediaPlayer.pause()
//                            binding.btnPlay.text = "Play"
//                        }
//                    }
//                }

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        mediaPlayer.release()
//    }
}