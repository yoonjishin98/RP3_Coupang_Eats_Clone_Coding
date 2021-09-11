package com.yoonji.coupangeatsproject.src.order_cart

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityOrderCartBinding
import com.yoonji.coupangeatsproject.src.main.MainActivity
import com.yoonji.coupangeatsproject.src.order_cart.adapter.OrderAdapter
import com.yoonji.coupangeatsproject.src.order_cart.model.OrderData
import com.yoonji.coupangeatsproject.src.restaurant.RestaurantActivity
import java.text.DecimalFormat

class OrderCartActivity : BaseActivity<ActivityOrderCartBinding>(ActivityOrderCartBinding::inflate) {

    private var orderAdapter = OrderAdapter(this)
    val orderDatas = mutableListOf<OrderData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences = getSharedPreferences("COUPANG EATS", MODE_PRIVATE)
        val totalPrice = sharedPreferences.getInt("RestaurantPrice", 0)
        val myFormatter : DecimalFormat = DecimalFormat("###,###")
        var formattedStringPrice :String = myFormatter.format(totalPrice.toString())

        binding.tvOrderCartTotalPrice.text = formattedStringPrice

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
            intent.putExtra("orderDone", 222)
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