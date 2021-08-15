package com.yoonji.coupangeatsproject.src.main.home.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.databinding.ItemHomeAdslideBinding
import com.yoonji.coupangeatsproject.src.main.home.HomeFragment


class AdSlideAdapater(context: Context, sliderItems:List<String>) :
    RecyclerView.Adapter<AdSlideAdapater.SliderViewHolder>() {

    private var mContext = context
    var sliderItems = mutableListOf<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder
        = SliderViewHolder(ItemHomeAdslideBinding.inflate(LayoutInflater.from(parent.context), parent, false) )

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(sliderItems[position]);
    }

    override fun getItemCount(): Int  = sliderItems.size

    inner class SliderViewHolder(private val binding: ItemHomeAdslideBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int) = with(binding) {
            try {
                Glide.with(mContext).load(item).into(imgvItemSlider)
            } catch (e: Exception) {
                Log.d("홈화면 광고이미지 슬라이드 ", "ERROR: " + e.message)
            }
        }


    }


}