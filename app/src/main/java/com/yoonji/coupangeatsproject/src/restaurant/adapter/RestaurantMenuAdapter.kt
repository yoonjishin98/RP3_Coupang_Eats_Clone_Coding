package com.yoonji.coupangeatsproject.src.restaurant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.databinding.ItemRestaurantMenuBinding
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantMenuData

class RestaurantMenuAdapter (private val context: Context) : RecyclerView.Adapter<RestaurantMenuAdapter.ViewHolder>(){
    var datas = mutableListOf<RestaurantMenuData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantMenuAdapter.ViewHolder
            = ViewHolder(ItemRestaurantMenuBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: RestaurantMenuAdapter.ViewHolder, position: Int){
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding: ItemRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RestaurantMenuData) = with(binding) {
            tvItemRestaurantMenuTitle.text = item.restaurantMenuTitle
            binding.rvRestaurantMenuDetail.adapter = RestaurantDetailAdapter(context)
            binding.rvRestaurantMenuDetail.setHasFixedSize(true)

        }
    }
}