package com.example.photosapplication.Adapter

import android.app.Activity
import android.app.AlertDialog
import android.app.RecoverableSecurityException
import android.content.ActivityNotFoundException
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photosapplication.DisplayActivity
import com.example.photosapplication.Model.Video
import com.example.photosapplication.databinding.ShoeVideoBinding
import com.google.gson.Gson
import java.io.File

class VideoAdapter(var context: Context, var videolist:MutableList<Video>,private val onShareClickListener: (Video) -> Unit):RecyclerView.Adapter<VideoAdapter.MyViewHolder>() {
    class MyViewHolder(var binding:ShoeVideoBinding) :RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding= ShoeVideoBinding.inflate(LayoutInflater.from(context), parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return videolist.size
    }

    @RequiresApi(Build.VERSION_CODES.Q)
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

        }
        holder.binding.share.setOnClickListener {
             onShareClickListener(video)
        }
    }
//    private fun showDialog(video: Video, position: Int) {
//        val builder = AlertDialog.Builder(context)
//        builder.setTitle("Rename")
//
//        // Create an EditText view for user input
//        val input = EditText(context)
//        input.setText(video.name) // Set the current name as the default text
//        builder.setView(input)
//
//        builder.setPositiveButton("Rename") { dialog, which ->
//            val uri = Uri.parse(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString() + "/" + video.id)
//            try {
////
//                    val newName = input.text.toString().trim() // Get the text entered by the user
//                    if (newName.isNotEmpty()) {
//                        videolist[position].name = newName
//                        notifyDataSetChanged() // Notify the adapter that data has changed
//                        Toast.makeText(context, "Video renamed", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(context, "Please enter a valid name", Toast.LENGTH_SHORT)
//                            .show()
//                    }
//
//            }
//                catch (e: SecurityException) {
//                    e.printStackTrace()
//                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    Toast.makeText(context, "Error deleting video", Toast.LENGTH_SHORT).show()
//                }
//
//        }
//
//
//            builder.setNegativeButton("Cancel") { dialog, which ->
//                dialog.dismiss()
//            }
//
//            val dialog = builder.create()
//            dialog.show()
//        }
private fun showDialog(video: Video, position: Int) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle("Rename")

    // Create an EditText view for user input
    val input = EditText(context)
    input.setText(video.name) // Set the current name as the default text
    builder.setView(input)

    builder.setPositiveButton("Rename") { dialog, which ->
        try {
            val newName = input.text.toString().trim()
            if (newName.isNotEmpty()) {
                val uri = Uri.parse(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString() + "/" + video.id)
                val values = ContentValues().apply {
                    put(MediaStore.Video.Media.DISPLAY_NAME, newName)
                }
                val rowsUpdated = context.contentResolver.update(uri, values, null, null)
                if (rowsUpdated > 0) {
                    videolist[position].name = newName
                    notifyDataSetChanged() // Notify the adapter that data has changed
                    Toast.makeText(context, "Video renamed", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to rename video", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please enter a valid name", Toast.LENGTH_SHORT).show()
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
            Toast.makeText(context, "Permission denied. Name not renamed.", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Error renaming video", Toast.LENGTH_SHORT).show()
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
            val uri = Uri.parse(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString() + "/" + video.id)
            try {
                val rowsDeleted = context.contentResolver.delete(uri, null, null)
                if (rowsDeleted > 0) {
                    videolist.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, videolist.size)
                    Toast.makeText(context, "Video deleted", Toast.LENGTH_SHORT).show()
                } else {
//                    Toast.makeText(context, "Failed to delete video", Toast.LENGTH_SHORT).show()
                }
            } catch (e: SecurityException) {
                e.printStackTrace()
                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(context, "Error deleting video", Toast.LENGTH_SHORT).show()
            }
            videolist.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, videolist.size)
        }

        builder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
//    private fun shareVideo(video: Video) {
//        val fileUri = Uri.parse(video.path)
//        val shareIntent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(Intent.EXTRA_STREAM, fileUri)
//            type = "video/*"
//        }
//        context.startActivity(Intent.createChooser(shareIntent, "Share Video"))
//    }
//private fun shareVideo(video: Video) {
//    val fileUri = FileProvider.getUriForFile(context, context.packageName + "com.example.photosapplication.provider", File(video.path))
//    val shareIntent = Intent().apply {
//        action = Intent.ACTION_SEND
//        putExtra(Intent.EXTRA_STREAM, fileUri)
//        type = "video/*"
//        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Grant read permission to other apps
//    }
//    context.startActivity(Intent.createChooser(shareIntent, "Share Video"))
//}
//private fun shareVideo(video: Video) {
//    val fileUri = FileProvider.getUriForFile(context, "${context.packageName}.provider", File(video.path))
//    val shareIntent = Intent().apply {
//        action = Intent.ACTION_SEND
//        putExtra(Intent.EXTRA_STREAM, fileUri)
//        type = "video/*"
//        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//    }
//
//    try {
//        context.startActivity(Intent.createChooser(shareIntent, "Share Video"))
//    } catch (e: ActivityNotFoundException) {
//        Toast.makeText(context, "No app found to handle this action", Toast.LENGTH_SHORT).show()
//    } catch (e: SecurityException) {
//        // Handle the recoverable security exception
//        val intentSender = e.userAction.actionIntent.intentSender
//        val launcher = registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                // Permission granted, retry sharing
//                shareVideo(video)
//            } else {
//                Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//        launcher.launch(IntentSenderRequest.Builder(intentSender).build())
//    }
//}
//

//    private fun shareImage(image: Image) {
//        val fileUri = Uri.parse(image.path)
//        val shareIntent = Intent().apply {
//            action = Intent.ACTION_SEND
//            putExtra(Intent.EXTRA_STREAM, fileUri)
//            type = "image/*"
//        }
//        context.startActivity(Intent.createChooser(shareIntent, "Share Image"))
//    }
//private fun shareAudio(audio: Audio) {
//    val fileUri = Uri.parse(audio.path)
//    val shareIntent = Intent().apply {
//        action = Intent.ACTION_SEND
//        putExtra(Intent.EXTRA_STREAM, fileUri)
//        type = "audio/*"
//    }
//    context.startActivity(Intent.createChooser(shareIntent, "Share Audio"))
//}



    companion object {
        const val PERMISSIONS_REQUEST_CODE = 123 // Any unique request code
    }




}


//    private fun showDialog(video: Video, position: Int) {
//        val builder = AlertDialog.Builder(context)
//        builder.setTitle("Rename")
//
//        // Create an EditText view for user input
//        val input = EditText(context)
//        input.setText(video.name) // Set the current name as the default text
//        builder.setView(input)
//
//        builder.setPositiveButton("Rename") { dialog, which ->
//            val newName = input.text.toString().trim() // Get the text entered by the user
//            if (newName.isNotEmpty()) {
//                videolist[position].name = newName
//                notifyDataSetChanged() // Notify the adapter that data has changed
//                Toast.makeText(context, "Video renamed", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(context, "Please enter a valid name", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        builder.setNegativeButton("Cancel") { dialog, which ->
//            dialog.dismiss()
//        }
//
//        val dialog = builder.create()
//        dialog.show()
//    }