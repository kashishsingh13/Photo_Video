package com.exmple.nestedrecycleapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.exmple.nestedrecycleapplication.Adapter.ImageAdapter
import com.exmple.nestedrecycleapplication.Model.Allcategory
import com.exmple.nestedrecycleapplication.Model.ImageItem
import com.exmple.nestedrecycleapplication.databinding.ActivityImageSliderBinding
import com.exmple.nestedrecycleapplication.databinding.ImageCardviewBinding
import kotlin.math.abs

class ImageSliderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityImageSliderBinding
    private var itemList = mutableListOf<ImageItem>()
    private lateinit var handler : Handler
    private lateinit var pagerAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityImageSliderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handler = Handler()
        binding.viewpager2.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable,2000)
            }
        })
        prepareData()
        setUpTransfomer()
        pagerAdapter= ImageAdapter(this,itemList, binding.viewpager2)
       binding.viewpager2.clipToPadding=false
        binding.viewpager2.clipChildren=false
        binding.viewpager2.offscreenPageLimit=3
        binding.viewpager2.adapter = pagerAdapter
        binding.viewpager2.getChildAt(0).overScrollMode = ViewPager2.OVER_SCROLL_NEVER

    }



    private fun prepareData() {
        itemList.add(ImageItem(image = R.drawable.rocky))
        itemList.add(ImageItem(image = R.drawable.hollywood1))
        itemList.add(ImageItem(image = R.drawable.bestofoscar1))
        itemList.add(ImageItem(image = R.drawable.moviedubbedinhindi1))
        itemList.add(ImageItem(image = R.drawable.hollywood3))
        itemList.add(ImageItem(image = R.drawable.rani))
    }
    private fun setUpTransfomer(){
        var transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{
            pagerAdapter,state->
            var r=1- abs(state)
            pagerAdapter.scaleY =0.85f +r *0.14f
        }
        binding.viewpager2.setPageTransformer(transformer)
    }
    private val runnable=Runnable{
        binding.viewpager2.currentItem=binding.viewpager2.currentItem+1
    }
}