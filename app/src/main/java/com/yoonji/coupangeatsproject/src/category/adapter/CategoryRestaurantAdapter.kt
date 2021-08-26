package com.yoonji.coupangeatsproject.src.category.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.databinding.ItemCategoryRestaurantBinding
import com.yoonji.coupangeatsproject.src.category.data.CategoryRestaurantData
import com.yoonji.coupangeatsproject.src.restaurant.RestaurantActivity

class CategoryRestaurantAdapter  (private val context: Context) : RecyclerView.Adapter<CategoryRestaurantAdapter.ViewHolder>(){

    var datas = mutableListOf<CategoryRestaurantData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryRestaurantAdapter.ViewHolder
            = ViewHolder(ItemCategoryRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: CategoryRestaurantAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding :ItemCategoryRestaurantBinding ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CategoryRestaurantData) = with(binding) {
            Glide.with(itemView).load(item.categoryMainImg).into(imgvItemCategoryMain)
            Glide.with(itemView).load(item.categoryUpImg).into(imgvItemCategoryUp)
            Glide.with(itemView).load(item.categoryDownImg).into(imgvCategoryDown)
            tvCategoryName.text = item.categoryName

            if(item.categoryReview != "") {
                tvItemCategoryReview.text = item.categoryReview
                imgvCategoryStar.visibility = View.VISIBLE
                imgvItemCategorySpot.visibility = View.VISIBLE
            }
            else {
                imgvCategoryStar.visibility = View.GONE
                imgvItemCategorySpot.visibility = View.GONE
                tvItemCategoryReview.visibility = View.GONE
            }

            if(item.categoryDistance != ""){
                tvItemCategoryDistance.text = item.categoryDistance
                tvItemCategoryKm.visibility = View.VISIBLE
            }else{
                tvItemCategoryDistance.visibility = View.GONE
                tvItemCategoryKm.visibility = View.GONE
                imgvItemCategorySpotTwo.visibility = View.GONE
            }

            tvItemCategoryDeliveryTime.text = item.categoryDeliveryTime
            tvItemCategoryDeliveryFee.text = item.categoryDeliveryFee

            if(item.categoryCheetahDelivery == "Y")
                imgvItemCategoryCheetah.visibility = View.VISIBLE
            else
                imgvItemCategoryCheetah.visibility = View.GONE

            if(item.categoryCoupon != "")
                btnItemCategoryCoupon.text = item.categoryCoupon
            else
                btnItemCategoryCoupon.visibility = View.GONE

            itemView.setOnClickListener{
                Intent(context, RestaurantActivity::class.java).apply {

                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

        }
    }


}