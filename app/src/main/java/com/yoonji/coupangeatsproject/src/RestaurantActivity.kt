package com.yoonji.coupangeatsproject.src

import android.R.color
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ActivityRestaurantBinding


class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>(ActivityRestaurantBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<View>(R.id.toolbar_restaurant) as androidx.appcompat.widget.Toolbar
        toolbar.title = "KFC 왕십리역사점"
        setSupportActionBar(toolbar)

        //툴바에 백키(<-)
        setSupportActionBar(binding.toolbarRestaurant)
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //툴바에 즐겨찾기, 공유 버튼
        binding.toolbarRestaurant.inflateMenu(R.menu.restaurant_toolbar_menu)
        binding.toolbarRestaurant.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.restaurant_menu_heart -> {

                    true
                }
                R.id.restaurant_menu_share -> {

                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)

        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
        }
    }

    override fun onResume() {
        super.onResume()


    }
}