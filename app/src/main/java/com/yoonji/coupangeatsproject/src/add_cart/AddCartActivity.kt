package com.yoonji.coupangeatsproject.src.add_cart

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.View.*
import com.google.android.material.appbar.AppBarLayout
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddCartBinding
import com.yoonji.coupangeatsproject.src.restaurant.RestaurantActivity


class AddCartActivity : BaseActivity<ActivityAddCartBinding>(ActivityAddCartBinding::inflate), AddCartActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<View>(R.id.toolbar_add_cart) as androidx.appcompat.widget.Toolbar
        setSupportActionBar(toolbar)

        //상태바 투명
        window?.decorView?.systemUiVisibility =
            SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT

        // 툴바 접혀졌을 때 여부에 따른 아이콘 및 타이틀 변화
        var isShow = true
        var scrollRange = -1
        binding.appBarAddCart.addOnOffsetChangedListener( AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = appBarLayout.totalScrollRange
            }

            if (scrollRange + verticalOffset == 0) {     //appbar가 접혔을 때, 툴바에 보이도록
                binding.tvAddCartToolbarTitle.visibility = View.VISIBLE
                binding.tvAddCartToolbarTitle.text = "베스트 치킨 버킷백"        //careful there should a space between double quote otherwise it wont work
                binding.imgvAddCartBack.imageTintList = ColorStateList.valueOf(this.getColor(R.color.black))
                binding.imgvAddCartShare.imageTintList = ColorStateList.valueOf(this.getColor(R.color.black))

                isShow = true

            }else if (isShow){      //appbar가 펼쳐졌을 때
                binding.tvAddCartToolbarTitle.visibility = View.INVISIBLE
                binding.imgvAddCartBack.imageTintList = ColorStateList.valueOf(this.getColor(R.color.white))
                binding.imgvAddCartShare.imageTintList = ColorStateList.valueOf(this.getColor(R.color.white))

                isShow = false
            }
        } )
    }


    override fun onResume() {
        super.onResume()

        var count= 1
        var priceTotal = 0
        var price = binding.tvAddCartPrice.text.toString()
        price = price
            .replace("원","")
            .replace(",","")
        val priceInt = price.toInt()
        priceTotal = priceInt

        binding.tvAddCart.setOnClickListener{
            val intent = Intent(this,RestaurantActivity::class.java)
            intent.putExtra("cartCount",count)
            intent.putExtra("cartPrice", priceTotal)
            intent.putExtra("cartCheck","letsOrder")
            startActivity(intent)
        }

        binding.imgvAddCartPlus.setOnClickListener {
            count++
            binding.tvAddCartCount.text = count.toString()

            if(count > 1) {
                binding.imgvAddCartMinus.setImageResource(R.drawable.ic_minus)
                priceTotal = priceInt * count
                binding.tvAddCartPrice.text = priceTotal.toString() + "원"
            }

            if(count < 2)
                binding.imgvAddCartMinus.setImageResource(R.drawable.ic_minus_grey)
        }

        binding.imgvAddCartMinus.setOnClickListener {
            if(count > 1) {
                priceTotal -= priceInt
                count--
                binding.tvAddCartCount.text = count.toString()
                binding.tvAddCartPrice.text = priceTotal.toString() + "원"
            }else{
                binding.imgvAddCartMinus.setImageResource(R.drawable.ic_minus_grey)
            }

            if(count == 1)
                binding.imgvAddCartMinus.setImageResource(R.drawable.ic_minus_grey)

        }

        binding.imgvAddCartBack.setOnClickListener {
            finish()
        }

    }
}