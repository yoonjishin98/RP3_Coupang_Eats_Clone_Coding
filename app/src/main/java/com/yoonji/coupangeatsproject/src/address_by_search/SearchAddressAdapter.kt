package com.yoonji.coupangeatsproject.src.address_by_search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoonji.coupangeatsproject.databinding.ItemAddressBySearchBinding

class SearchAddressAdapter  (private val context: Context) : RecyclerView.Adapter<SearchAddressAdapter.ViewHolder>(){
    var datas = mutableListOf<SearchAddressData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAddressAdapter.ViewHolder
            = ViewHolder(ItemAddressBySearchBinding.inflate(LayoutInflater.from(parent.context), parent,false))

    override fun onBindViewHolder(holder: SearchAddressAdapter.ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int  = datas.size

    inner class ViewHolder(private val binding : ItemAddressBySearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SearchAddressData) = with(binding) {
            tvItemSearchAddress.text = item.searchAddress
            tvItemSearchAddress.text = item.searchRoadAddress

        }
    }
}