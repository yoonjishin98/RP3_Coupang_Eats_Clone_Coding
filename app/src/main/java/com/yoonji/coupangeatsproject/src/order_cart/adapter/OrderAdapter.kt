package com.yoonji.coupangeatsproject.src.order_cart.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoonji.coupangeatsproject.databinding.ItemOrderCartBinding
import com.yoonji.coupangeatsproject.src.order_cart.model.OrderData

class OrderAdapter (private val context: Context) : RecyclerView.Adapter<OrderAdapter.ViewHolder>(){
    var datas = mutableListOf<OrderData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.ViewHolder
            = ViewHolder(ItemOrderCartBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: OrderAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int  = datas.size

    inner class ViewHolder(private val binding: ItemOrderCartBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OrderData) = with(binding) {
            tvItemOrderTitle.text = item.orderTitle
            tvItemOrderDescrip.text = item.orderDescription
            tvItemOrderPrice.text = item.orderPrice
            tvItemOrderCount.text = item.orderCount.toString()
        }
    }

}