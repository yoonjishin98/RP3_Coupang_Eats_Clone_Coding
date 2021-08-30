package com.yoonji.coupangeatsproject.src.order_cart

import android.content.Intent
import android.os.Bundle
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityOrderCartBinding
import com.yoonji.coupangeatsproject.src.main.MainActivity
import com.yoonji.coupangeatsproject.src.order_cart.adapter.OrderAdapter
import com.yoonji.coupangeatsproject.src.order_cart.model.OrderData
import com.yoonji.coupangeatsproject.src.restaurant.RestaurantActivity
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantReviewData

class OrderCartActivity : BaseActivity<ActivityOrderCartBinding>(ActivityOrderCartBinding::inflate) {

    private var orderAdapter = OrderAdapter(this)
    val orderDatas = mutableListOf<OrderData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initOrderRecycler()

    }

    override fun onResume() {
        super.onResume()

        binding.imgvOrderCartFinish.setOnClickListener {
            finish()
        }

        binding.tvOrderAddMenu.setOnClickListener {
            val intent = Intent(this,RestaurantActivity::class.java)
            startActivity(intent)
        }

        binding.layoutOrderCart.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("total", "ㅇㅇ")
            startActivity(intent)
        }

        binding.checkOrder.setOnClickListener {
            binding.checkOrder.setImageResource(R.drawable.checkbox_custom)
        }

    }

    fun initOrderRecycler(){
       // orderAdapter = OrderAdapter(this)
        binding.rvOrderMenuDetail.adapter = orderAdapter

        orderDatas.apply {
            add(OrderData(orderTitle = "실속세트",orderDescription = "우삽겹 500g, 칠리새우+또띠아 추가", orderPrice = "26,900원",orderCount = 2))
            add(OrderData(orderTitle = "실속세트",orderDescription = "우삽겹 500g, 칠리새우+또띠아 추가", orderPrice = "26,900원",orderCount = 2))
            add(OrderData(orderTitle = "실속세트",orderDescription = "우삽겹 500g, 칠리새우+또띠아 추가", orderPrice = "26,900원",orderCount = 2))
        }
        orderAdapter.datas = orderDatas
    }
}