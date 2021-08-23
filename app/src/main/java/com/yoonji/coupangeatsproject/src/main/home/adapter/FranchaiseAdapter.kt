package com.yoonji.coupangeatsproject.src.main.home.adapter

import android.content.Context
import android.view.LayoutInflater
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
            tvHomeFranchaiseReview.text = item.franchaiseReview
            tvHomeFranchaiseDistance.text = item.franchaiseDistance
            if(item.franchaiseDeliveryFee==0){
                tvHomeFranchaiseDeliveryFee.text = "무료배달"
            }else{
                tvHomeFranchaiseDeliveryFee.text = item.franchaiseDeliveryFee.toString() + "원"
            }


        }
    }

}