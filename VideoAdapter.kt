package com.example.photosapplication.Adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photosapplication.DisplayActivity
import com.example.photosapplication.Model.Video
import com.example.photosapplication.databinding.ShoeVideoBinding
import com.google.gson.Gson

class VideoAdapter(var context: Context, var videolist:MutableList<Video>):RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {
    class MyViewHolder(var binding:ShoeVideoBinding) :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding= ShoeVideoBinding.inflate(LayoutInflater.from(context), parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return videolist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var video = videolist[position]
        holder.binding.name.text = video.name
        holder.binding.menu.setOnClickListener {
          showDialog(video,position)
            notifyDataSetChanged()
        }

        Glide.with(context).asBitmap()
            .load((video.path)) // Assuming imagePaths contain file paths
            .centerCrop()
            .into(holder.binding.video)
        holder.binding.video.setOnClickListener {
            var intent = Intent(context, DisplayActivity::class.java)
            intent.putExtra("video", videolist.toTypedArray())
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
        holder.binding.delete.setOnClickListener {
            showDialogdelete(video,position)
            notifyDataSetChanged()
        }
    }

    private fun showDialog(video: Video, position: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Rename")

        // Create an EditText view for user input
        val input = EditText(context)
        input.setText(video.name) // Set the current name as the default text
        builder.setView(input)

        builder.setPositiveButton("Rename") { dialog, which ->
            val newName = input.text.toString().trim() // Get the text entered by the user
            if (newName.isNotEmpty()) {
                videolist[position].name = newName
                notifyDataSetChanged() // Notify the adapter that data has changed
                Toast.makeText(context, "Video renamed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Please enter a valid name", Toast.LENGTH_SHORT).show()
            }
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
    private fun showDialogdelete(video: Video, position: Int) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete this video?")

        builder.setPositiveButton("Delete") { dialog, which ->
            videolist.removeAt(position)
          notifyItemRemoved(position) // Notify the adapter of the item removal
            notifyItemRangeChanged(position,videolist.size)
            notifyDataSetChanged()// Notify the adapter of the data change
            saveVideoListToStorage(videolist)
            Toast.makeText(context, "Video deleted", Toast.LENGTH_SHORT).show()
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
    private fun saveVideoListToStorage(videoList: MutableList<Video>) {

        val sharedPreferences = context.getSharedPreferences("VideoPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Convert the video list to JSON and save it
        val gson = Gson()
        val json = gson.toJson(videoList)
        editor.putString("videoList", json)
        editor.apply()
    }

}