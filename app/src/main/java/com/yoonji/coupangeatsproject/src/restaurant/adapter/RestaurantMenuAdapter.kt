package com.yoonji.coupangeatsproject.src.restaurant.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ItemRestaurantMenuBinding
import com.yoonji.coupangeatsproject.src.restaurant.RestaurantRecyclerViewDivider
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantMenuData
import kotlinx.android.synthetic.main.item_restaurant_menu.view.*

class RestaurantMenuAdapter (private val context: Context) : RecyclerView.Adapter<RestaurantMenuAdapter.ViewHolder>(){
    var datas = mutableListOf<RestaurantMenuData>()

    lateinit var menuDetailAdapter : RestaurantDetailAdapter

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RestaurantMenuAdapter.ViewHolder
            = ViewHolder(ItemRestaurantMenuBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: RestaurantMenuAdapter.ViewHolder, position: Int){
        holder.bind(datas[position])

        // 마지막 divider는 사라지도록
        if(position == datas.size-1)
            holder.itemView.view_menu.visibility = GONE
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(private val binding: ItemRestaurantMenuBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RestaurantMenuData) = with(binding) {
            tvItemRestaurantMenuTitle.text = item.restaurantMenuTitle

            menuDetailAdapter = RestaurantDetailAdapter(context)
            val listForMenuDetail = item.menuDetailArrayList
            menuDetailAdapter.datas = listForMenuDetail
            binding.rvRestaurantMenuDetail.adapter = menuDetailAdapter
            menuDetailAdapter.notifyDataSetChanged()

            // detail menu에 얇은 divider 추가
            val color = context.getColor(R.color.greyForThinDivider)
            binding.rvRestaurantMenuDetail.addItemDecoration(RestaurantRecyclerViewDivider(2f,50f, color))

        }
    }
}