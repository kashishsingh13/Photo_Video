package com.example.photosapplication

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.Manifest
import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photosapplication.Adapter.VideoAdapter
import com.example.photosapplication.Model.Video
import com.example.photosapplication.databinding.FragmentPhotoBinding
import com.example.photosapplication.databinding.FragmentVideoBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding
    private lateinit var videoAdapter: VideoAdapter
    private val videoList = mutableListOf<Video>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         videoList.clear()
        videoAdapter = VideoAdapter(requireContext(), videoList)
        binding.recycle.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = videoAdapter
        }

        if (hasPermission()) {
            loadVideos()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSIONS_REQUEST_CODE)
        }
    }


    private fun hasPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadVideos()
            }
        }
    }

    private fun loadVideos() {
        videoList.clear()
        val cursor = requireContext().contentResolver.query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
            )
            cursor?.use { c ->
                val idColumn = c.getColumnIndexOrThrow(MediaStore.Video.Media._ID)
                val nameColumn = c.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME)
                val pathColumn = c.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
                var time = c.getColumnIndex(MediaStore.Video.Media.DURATION)
                val artColumn = c.getColumnIndexOrThrow(MediaStore.Video.Media.ARTIST)
                while (c.moveToNext()) {
                    val id = c.getLong(idColumn)
                    val name = c.getString(nameColumn)
                    val path = c.getString(pathColumn)
                    val artUri = Uri.parse(c.getString(pathColumn))
                    val video = Video(id, name, path, artUri)
                    videoList.add(video)

                }
            }

            videoAdapter.notifyDataSetChanged()


    }

    companion object {
        private const val PERMISSIONS_REQUEST_CODE = 121
    }
}