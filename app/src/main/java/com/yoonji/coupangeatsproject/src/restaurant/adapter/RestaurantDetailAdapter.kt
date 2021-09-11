package com.yoonji.coupangeatsproject.src.restaurant.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.databinding.ItemRestaurantMenuDetailBinding
import com.yoonji.coupangeatsproject.src.add_cart.AddCartActivity
import com.yoonji.coupangeatsproject.src.restaurant.data.RestaurantDetailData

class RestaurantDetailAdapter (private val context: Context) : RecyclerView.Adapter<RestaurantDetailAdapter.ViewHolder>(){
    var datas = mutableListOf<RestaurantDetailData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantDetailAdapter.ViewHolder
        = ViewHolder(ItemRestaurantMenuDetailBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: RestaurantDetailAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int  = datas.size

    inner class ViewHolder(private val binding: ItemRestaurantMenuDetailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RestaurantDetailData) = with(binding) {
            tvItemRestaurantDetailTitle.text = item.restaurantDetailTitle
            tvItemRestaurantDetailPrice.text = item.restaurantDetailPrice

            if(item.restaurantDetailImg == null || item.restaurantDetailImg == "")
                imgvItemRestaurantDetail.visibility = GONE
            else {
                imgvItemRestaurantDetail.visibility = VISIBLE
                Glide.with(itemView).load(item.restaurantDetailImg).into(imgvItemRestaurantDetail)
            }

            if(item.restaurantDetailDescrip == null || item.restaurantDetailDescrip == "")
                tvItemRestaurantDetailDescrip.visibility = GONE
            else {
                tvItemRestaurantDetailDescrip.visibility = VISIBLE
                tvItemRestaurantDetailDescrip.text = item.restaurantDetailDescrip
            }

            itemView.setOnClickListener{
                Intent(context, AddCartActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

        }
    }

}