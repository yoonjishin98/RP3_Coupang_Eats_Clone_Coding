package com.yoonji.coupangeatsproject.src.category.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.databinding.ItemCategoryFoodTypeBinding
import com.yoonji.coupangeatsproject.src.category.data.CategoryFoodTypeData

class CategoryFoodTypeAdapter (private val context: Context) : RecyclerView.Adapter<CategoryFoodTypeAdapter.ViewHolder>(){

    var datas = mutableListOf<CategoryFoodTypeData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryFoodTypeAdapter.ViewHolder
            = ViewHolder(ItemCategoryFoodTypeBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: CategoryFoodTypeAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding : ItemCategoryFoodTypeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CategoryFoodTypeData) = with(binding) {
            tvCategoryFoodType.text = item.categoryFoodTypeTitle
            Glide.with(itemView).load(item.categoryFoodTypeImg).into(imgvCategoryFoodType)
        }
    }


}