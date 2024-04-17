package com.example.module7application.Que4

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.example.module7application.Model.Music
import com.example.module7application.R
import com.example.module7application.databinding.ActivityAddSongBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import java.io.File

class AddSongActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddSongBinding
    private lateinit var mRef: DatabaseReference
    private var imageUri: Uri? = null
    private var musicUri :Uri?=null
    private var music: Music? = null
    private lateinit var storageRef: StorageReference

    private var gellaryContract=registerForActivityResult(ActivityResultContracts.GetContent()){
        binding.image.setImageURI(it)
        imageUri=it
    }
    private val pickSongLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                // Use the selected file URI to play the song
                musicUri=uri

            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddSongBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mRef = Firebase.database.reference
        storageRef= Firebase.storage.reference

        binding.image.setOnClickListener {
            gellaryContract.launch("image/*")
        }
        binding.music.setOnClickListener {
//            createImageUri()
            pickSong()
        }

        binding.add.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val singer = binding.singer.text.toString().trim()

            if (musicUri != null && imageUri != null) {
                getDownloadUrl(imageUri!!) { imageUrl ->
                    if (imageUrl != null) {
                        getDownloadUrl(musicUri!!) { musicUrl ->
                            if (musicUrl != null) {
                                binding.music.setText(musicUrl)
                                addMusic(name, singer, imageUrl, musicUrl)
                            } else {
                                Toast.makeText(this, "Failed to get music URL", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        Toast.makeText(this, "Failed to get image URL", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please select both an image and a music file", Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun pickSong() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "audio/*"
        }
        pickSongLauncher.launch(intent)
    }
    private fun getDownloadUrl(uri: Uri, onComplete: (String?) -> Unit) {
        val dirName = "uploads"
        val fileName = "${System.currentTimeMillis()}_${uri.lastPathSegment}"
        val fileReference = storageRef.child(dirName).child(fileName)

        val uploadTask = fileReference.putFile(uri)

        uploadTask.continueWithTask { task ->
            if (!task.isSuccessful) {
                task.exception?.let {
                    throw it
                }
            }

            // File successfully uploaded, getting download URI
            fileReference.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val downloadUri = task.result.toString()
                onComplete(downloadUri)
            } else {
                onComplete(null)
            }
        }
    }
    private fun addMusic(name: String, singer: String, imageUrl: String, musicUrl: String) {
        mRef.push().key?.let {
            val music = Music(name = name, singername = singer, image = imageUrl, music = musicUrl, id = it)

            mRef.child("music-node").child(it).setValue(music).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Music added", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this, "Failed to add music", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun createImageUri(): Uri? {
        var fileName = "${System.currentTimeMillis()}.mp3"

        var musicFile = File(filesDir, fileName)

        return FileProvider.getUriForFile(
            this,
            "com.example.module7application.file_provider",
            musicFile
        )
    }
}