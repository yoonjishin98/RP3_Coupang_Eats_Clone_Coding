package com.yoonji.coupangeatsproject.src.order

import android.os.Bundle
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ActivityAddCartBinding
import com.yoonji.coupangeatsproject.databinding.ActivityOrderCartBinding
import com.yoonji.coupangeatsproject.src.order.adapter.OrderAdapter
import com.yoonji.coupangeatsproject.src.order.model.OrderData
import com.yoonji.coupangeatsproject.src.restaurant.adapter.RestaurantMenuAdapter
import com.yoonji.coupangeatsproject.src.restaurant.adapter.RestaurantReviewAdapter
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantDetailData
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantReviewData

class OrderCartActivity : BaseActivity<ActivityOrderCartBinding>(ActivityOrderCartBinding::inflate) {

    lateinit var orderAdapter : OrderAdapter
    val orderDatas = mutableListOf<OrderData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOrderRecycler()

    }

    fun initOrderRecycler(){
        orderAdapter = OrderAdapter(this)
        binding.rvOrderMenuDetail.adapter = orderAdapter

        orderDatas.apply {
            add(OrderData(orderTitle = "실속세트",orderDescription = "우삽겹 500g, 칠리새우+또띠아 추가", orderPrice = "26,900원",orderCount = 2))
            add(OrderData(orderTitle = "실속세트",orderDescription = "우삽겹 500g, 칠리새우+또띠아 추가", orderPrice = "26,900원",orderCount = 2))
            add(OrderData(orderTitle = "실속세트",orderDescription = "우삽겹 500g, 칠리새우+또띠아 추가", orderPrice = "26,900원",orderCount = 2))
        }
        orderAdapter.datas = orderDatas
    }
}