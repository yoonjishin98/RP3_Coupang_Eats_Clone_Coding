package com.yoonji.coupangeatsproject.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.databinding.ItemHomeFoodTypeBinding
import com.yoonji.coupangeatsproject.src.main.home.data.FoodTypeData

class FoodTypeAdapter (private val context: Context) : RecyclerView.Adapter<FoodTypeAdapter.ViewHolder>(){

     var datas = mutableListOf<FoodTypeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodTypeAdapter.ViewHolder
            = ViewHolder(ItemHomeFoodTypeBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: FoodTypeAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding : ItemHomeFoodTypeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FoodTypeData) = with(binding) {
            tvHomeFoodType.text = item.typeTitle
            Glide.with(itemView).load(item.typeImg).into(imgvHomeFoodType)

        }
    }

}