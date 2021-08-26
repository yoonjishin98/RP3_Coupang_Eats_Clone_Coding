package com.yoonji.coupangeatsproject.src.main.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.databinding.ItemHomeChooseRestaurantBinding
import com.yoonji.coupangeatsproject.src.main.home.data.ChooseRestaurantData
import com.yoonji.coupangeatsproject.src.restaurant.RestaurantActivity

class ChooseRestaurantAdapter (private val context: Context) : RecyclerView.Adapter<ChooseRestaurantAdapter.ViewHolder>(){

    var datas = mutableListOf<ChooseRestaurantData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChooseRestaurantAdapter.ViewHolder
            = ViewHolder(ItemHomeChooseRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: ChooseRestaurantAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }
  
    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding : ItemHomeChooseRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ChooseRestaurantData) = with(binding) {
            Glide.with(itemView).load(item.restaurantMainImg).into(imgvHomeChooseMain)
            Glide.with(itemView).load(item.restaurantUpImg).into(imgvHomeChooseUp)
            Glide.with(itemView).load(item.restaurantDownImg).into(imgvHomeChooseDown)
            tvHomeChooseName.text = item.restaurantName

            if(item.restaurantReview != "") {
                tvHomeChooseReview.text = item.restaurantReview
                imgvHomeChooseStar.visibility = VISIBLE
                imgvHomeChooseSpot.visibility = VISIBLE
            }
            else {
                imgvHomeChooseSpot.visibility = GONE
                imgvHomeChooseStar.visibility = GONE
                imgvHomeChooseSpot.visibility = GONE
                tvHomeChooseReview.visibility = GONE
            }

            if(item.restaurantDistance != ""){
                tvHomeChooseDistance.text = item.restaurantDistance
                tvItemChooseKm.visibility = VISIBLE
            }else{
                tvHomeChooseDistance.visibility = GONE
                tvItemChooseKm.visibility = GONE
                imgvHomeChooseSpotTwo.visibility = GONE
            }

            tvHomeChooseDeliveryTime.text = item.restaurantDeliveryTime
            tvHomeChosseDeliveryFee.text = item.restaurantDeliveryFee

            if(item.restaurantCheetahDelivery == "Y")
                imgvHomeChooseCheetah.visibility = VISIBLE
            else
                imgvHomeChooseCheetah.visibility = GONE

            if(item.restaurantCoupon != "")
                btnHomeChooseCoupon.text = item.restaurantCoupon
            else
                btnHomeChooseCoupon.visibility = GONE

            itemView.setOnClickListener{
                Intent(context, RestaurantActivity::class.java).apply {
                    putExtra("title", item.restaurantName)
//                    putExtra("distance",item.restaurantDistance)
//                    putExtra("reviewScore", item.restaurantReview)
//                    putExtra("deliveryFee",item.restaurantDeliveryFee)
//                    putExtra("deliveryTime",item.restaurantDeliveryTime)

                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

        }
    }

}