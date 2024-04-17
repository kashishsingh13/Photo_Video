package com.example.module7application.Adapter

import android.content.Context
import android.content.Intent
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.module7application.Model.Music
import com.example.module7application.Que4.PlaySongActivity
import com.example.module7application.R
import com.example.module7application.databinding.BottomsheetBinding
import com.example.module7application.databinding.MusicLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso

class MusicAdapter(var context: Context, var musicList: MutableList<Music>):
    RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {
//    var itemDeleteClickListener: ((position: Int, category:Category) -> Unit)? = null
//    var itemUpdateClickListener: ((position: Int, category:Category) -> Unit)? = null



    class MyViewHolder(var binding: MusicLayoutBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var binding = MusicLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {
        val music = musicList[position]
            holder.binding.name.text = music.name
            holder.binding.singer.text = music.singername
            if (!music.image.isNullOrEmpty()) {
                Picasso.get().load(music.image).into(holder.binding.songimage)

        }
//

        holder.binding.click.setOnClickListener {
                       showBottomSheetDialog(position)
        }
        holder.binding.card.setOnClickListener {
            var intent = Intent(context, PlaySongActivity::class.java)
             intent.putExtra("music", music)
            context.startActivity(intent)
        }
    }
    private fun showBottomSheetDialog(position: Int,) {
        var bind = BottomsheetBinding.inflate(LayoutInflater.from(context))

        var dialog = BottomSheetDialog(context)
        dialog.setContentView(bind.root)
        dialog.show()

        bind.update.setOnClickListener {
                dialog.dismiss()

        }
        bind.delete.setOnClickListener {
            dialog.dismiss()
        }

    }




    fun deleteItem(position: Int){
        musicList.removeAt(position)
        notifyItemRemoved(position)
    }

//    fun updateList(userList: MutableList<Employee>) {
//        this.userList =userList
//        notifyDataSetChanged()
//
//    }
}