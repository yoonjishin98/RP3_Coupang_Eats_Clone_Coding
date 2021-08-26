package com.yoonji.coupangeatsproject.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.databinding.ItemHomeFranchiseBinding
import com.yoonji.coupangeatsproject.src.main.home.data.FranchaiseData

class FranchaiseAdapter (private val context: Context) : RecyclerView.Adapter<FranchaiseAdapter.ViewHolder>(){

    var fdatas = mutableListOf<FranchaiseData>()

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): FranchaiseAdapter.ViewHolder
        = ViewHolder(ItemHomeFranchiseBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: FranchaiseAdapter.ViewHolder, position: Int) {
        holder.bind(fdatas[position])
    }

    override fun getItemCount(): Int = fdatas.size

    inner class ViewHolder(private val binding: ItemHomeFranchiseBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FranchaiseData) = with(binding) {

            Glide.with(itemView).load(item.franchaiseImg).into(imgvHomeFranchaise)
            tvHomeFranchaiseTitle.text = item.franchaiseTitle

            if(item.franchaiseReview == ""){
                tvHomeFranchaiseReview.visibility = GONE
                imgvItemFranchaiseStar.visibility = GONE
                imgvItemFranchaiseSpot.visibility = GONE
            }else{
                imgvItemFranchaiseSpot.visibility = VISIBLE
                tvHomeFranchaiseReview.text = item.franchaiseReview
                tvHomeFranchaiseReview.visibility = VISIBLE
                imgvItemFranchaiseStar.visibility = VISIBLE
            }

            if(item.franchaiseDistance == "") {
                tvHomeFranchaiseDistance.visibility = INVISIBLE
                tvItemFranchaiseKm.visibility = INVISIBLE
                imgvItemFranchaiseSpot.visibility = INVISIBLE
            }else{
                imgvItemFranchaiseSpot.visibility = GONE
                tvHomeFranchaiseDistance.text = item.franchaiseDistance
            }

            when {
                item.franchaiseDeliveryFee== "무료배달" -> {
                    tvHomeFranchaiseDeliveryFee.visibility = VISIBLE
                    tvHomeFranchaiseDeliveryFee.text = "무료배달"
                }
                item.franchaiseDeliveryFee.contains("쿠폰") -> {
                    tvHomeFranchaiseDeliveryFee.visibility = GONE
                    btnHomeChooseFranchiaseCoupon.visibility = VISIBLE
                }
                item.franchaiseDeliveryFee.contains("배달비") -> {
                    btnHomeChooseFranchiaseCoupon.visibility = GONE
                    tvHomeFranchaiseDeliveryFee.visibility = VISIBLE
                    tvHomeFranchaiseDeliveryFee.text = item.franchaiseDeliveryFee
                }
            }

        }
    }

}