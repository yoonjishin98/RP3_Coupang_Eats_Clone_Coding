package com.yoonji.coupangeatsproject.src

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ItemRestaurantReviewBinding

class RestaurantReviewAdapter (private val context: Context) : RecyclerView.Adapter<RestaurantReviewAdapter.ViewHolder>(){
    var datas = mutableListOf<RestaurantReviewData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantReviewAdapter.ViewHolder
            = ViewHolder(ItemRestaurantReviewBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: RestaurantReviewAdapter.ViewHolder, position: Int)
        = holder.bind(datas[position])

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding: ItemRestaurantReviewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RestaurantReviewData) = with(binding) {
            Glide.with(itemView).load(item.reviewImg).into(imgvItemReview)
            tvItemReview.text = item.reviewTitle

            if(item.reviewStarScore == 1){
                binding.imgvItemReviewScoreT.setColorFilter(R.color.greyForReview)
                binding.imgvItemReviewScoreTh.setColorFilter(R.color.greyForReview)
                binding.imgvItemReviewScoreF.setColorFilter(R.color.greyForReview)
                binding.imgvItemReviewScoreFi.setColorFilter(R.color.greyForReview)
            }else if(item.reviewStarScore == 2){
                binding.imgvItemReviewScoreTh.setColorFilter(R.color.greyForReview)
                binding.imgvItemReviewScoreF.setColorFilter(R.color.greyForReview)
                binding.imgvItemReviewScoreFi.setColorFilter(R.color.greyForReview)
            } else if(item.reviewStarScore == 3){
                binding.imgvItemReviewScoreF.setColorFilter(R.color.greyForReview)
                binding.imgvItemReviewScoreFi.setColorFilter(R.color.greyForReview)
            }else if(item.reviewStarScore == 4){
                binding.imgvItemReviewScoreFi.setColorFilter(R.color.greyForReview)
            }

        }
    }

}