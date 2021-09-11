package com.yoonji.coupangeatsproject.src.restaurant.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View.*
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ItemRestaurantReviewBinding
import com.yoonji.coupangeatsproject.src.restaurant.data.RestaurantReviewData

class RestaurantReviewAdapter (private val context: Context) : RecyclerView.Adapter<RestaurantReviewAdapter.ViewHolder>(){
    var datas = mutableListOf<RestaurantReviewData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder
            = ViewHolder(ItemRestaurantReviewBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
        = holder.bind(datas[position])

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding: ItemRestaurantReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RestaurantReviewData) = with(binding) {
            if(item.reviewImg == null || item.reviewImg == ""){
                imgvItemReview.visibility = INVISIBLE
            }else{
                imgvItemReview.visibility = VISIBLE
                Glide.with(itemView).load(item.reviewImg).into(imgvItemReview)
            }

            tvItemReview.text = item.reviewTitle

            if(item.reviewStarScore == 1){
                binding.imgvItemReviewScoreT.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
                binding.imgvItemReviewScoreTh.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
                binding.imgvItemReviewScoreF.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
                binding.imgvItemReviewScoreFi.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
            }else if(item.reviewStarScore == 2){
                binding.imgvItemReviewScoreTh.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
                binding.imgvItemReviewScoreF.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
                binding.imgvItemReviewScoreFi.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
            } else if(item.reviewStarScore == 3){
                binding.imgvItemReviewScoreF.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
                binding.imgvItemReviewScoreFi.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
            }else if(item.reviewStarScore == 4){
                binding.imgvItemReviewScoreFi.imageTintList = ColorStateList.valueOf(context.getColor(R.color.greyForReview))
            }

        }
    }

}