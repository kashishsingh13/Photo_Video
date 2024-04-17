package com.exmple.nestedrecycleapplication.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.exmple.nestedrecycleapplication.Model.ImageItem
import com.exmple.nestedrecycleapplication.databinding.ImageCardviewBinding

class ImageAdapter(var context: Context, var itemList: MutableList<ImageItem>, var viewpager:ViewPager2):RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    class ViewHolder(val binding:ImageCardviewBinding):RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = ImageCardviewBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=itemList[position]
        holder.binding.image.setImageResource(item.image)
        if (position == itemList.size-1){
            viewpager.post(runnable())


        }

    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    private fun runnable() = Runnable{
        itemList.addAll(itemList)
        notifyDataSetChanged()

    }
}