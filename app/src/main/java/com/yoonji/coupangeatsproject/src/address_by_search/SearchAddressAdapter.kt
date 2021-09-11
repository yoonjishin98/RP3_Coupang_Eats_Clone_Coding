package com.yoonji.coupangeatsproject.src.address_by_search

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yoonji.coupangeatsproject.databinding.ItemAddressBySearchBinding
import com.yoonji.coupangeatsproject.src.address_detail.AddressDetailActivity

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
            tvItemSearchRoad.text = item.searchRoadAddress

            itemView.setOnClickListener{
                Intent(context, AddressDetailActivity::class.java).apply {
                    putExtra("address",item.searchAddress)
                    putExtra("roadAddress",item.searchRoadAddress)

                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { context.startActivity(this) }
            }

        }
    }
}