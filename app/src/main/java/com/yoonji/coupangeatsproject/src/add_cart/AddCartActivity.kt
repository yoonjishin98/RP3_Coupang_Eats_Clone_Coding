package com.yoonji.coupangeatsproject.src.add_cart

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddCartBinding
import com.yoonji.coupangeatsproject.src.restaurant.RestaurantActivity


class AddCartActivity : BaseActivity<ActivityAddCartBinding>(ActivityAddCartBinding::inflate) {

    var count= 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //툴바에 뒤로 가기
        setSupportActionBar(binding.toolbarAddCart)
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//        binding.appBarAddCart.addOnOffsetChangedListener( AppBarLayout.OnOffsetChangedListener {appBarLayout, verticalOffset ->
//            if ( (binding.appBarAddCart.height + verticalOffset) < (2 * ViewCompat.getMinimumHeight(binding.appBarAddCart) ) ) {
//                binding.toolbarAddCart.navigationIcon?.setColorFilter(resources.getColor(R.color.white),PorterDuff.Mode.SRC_ATOP)
//            } else {
//
//            }
//        } )

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_cart_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {      //뒤로 가기
                finish()
                true
            }
            R.id.add_cart_menu_share -> {

                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        binding.tvAddCart.setOnClickListener{
            val intent = Intent(this,RestaurantActivity::class.java)
            intent.putExtra("showOrderCartActivityBtn","1000")
            startActivity(intent)
        }

        binding.imgvAddCartPlus.setOnClickListener {
            count++
            binding.tvAddCartCount.text = count.toString()
            if(binding.tvAddCartCount.text.toString().toInt() >= 2)
                binding.imgvAddCartMinus.setImageResource(R.drawable.ic_minus)
            else
                binding.imgvAddCartMinus.setImageResource(R.drawable.ic_minus_grey)
        }

        binding.imgvAddCartMinus.setOnClickListener {
            if(binding.tvAddCartCount.text.toString().toInt() > 1){
                count--
                binding.tvAddCartCount.text = count.toString()
            }else{
                binding.imgvAddCartMinus.setImageResource(R.drawable.ic_minus_grey)
            }
        }

        if( binding.tvAddCartCount.text.toString().toInt() < 1){
            binding.imgvAddCartMinus.setImageResource(R.drawable.ic_minus_grey)
        }

    }
}