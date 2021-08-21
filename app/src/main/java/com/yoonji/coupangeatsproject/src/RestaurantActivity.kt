package com.yoonji.coupangeatsproject.src

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ScrollView
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ActivityRestaurantBinding


class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>(ActivityRestaurantBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var reviewAdapter : RestaurantReviewAdapter
        val reviewDatas = mutableListOf<RestaurantReviewData>()

        val toolbar = findViewById<View>(R.id.toolbar_restaurant) as androidx.appcompat.widget.Toolbar
        toolbar.title = "KFC 왕십리역사점"
        setSupportActionBar(toolbar)

        //툴바에 뒤로 가기
        setSupportActionBar(binding.toolbarRestaurant)
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //툴바에 즐겨찾기, 공유 버튼
//        binding.toolbarRestaurant.inflateMenu(R.menu.restaurant_toolbar_menu)
//        binding.toolbarRestaurant.setOnMenuItemClickListener {
//            when (it.itemId) {
//                R.id.restaurant_menu_heart -> {
//
//                    true
//                }
//                R.id.restaurant_menu_share -> {
//
//                    true
//                }
//                else -> {
//                    super.onOptionsItemSelected(it)
//                }
//            }
//        }

        reviewAdapter = RestaurantReviewAdapter(this)
        binding.rvRestaurantReview.adapter = reviewAdapter

        reviewDatas.apply{
            add(RestaurantReviewData(reviewImg = R.drawable.img_restaurant_review,reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히",
            reviewStarScore = 3))
            add(RestaurantReviewData(reviewImg = R.drawable.img_restaurant_review,reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히dfdasfasfsfsfasfsdfsdfsf",
                reviewStarScore = 5))
            add(RestaurantReviewData(reviewImg = R.drawable.img_restaurant_review,reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히",
                reviewStarScore = 1))
        }

        reviewAdapter.datas = reviewDatas

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.restaurant_toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {      //뒤로 가기
                finish()
                true
            }
            R.id.restaurant_menu_heart -> {

                true
            }
            R.id.restaurant_menu_share -> {

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

        //탭 레이아웃
        binding.tabRestaurant.addTab( binding.tabRestaurant.newTab().setText("추천메뉴"))
        binding.tabRestaurant.addTab( binding.tabRestaurant.newTab().setText("신메뉴"))
        binding.tabRestaurant.addTab( binding.tabRestaurant.newTab().setText("치킨"))
        binding.tabRestaurant.addTab( binding.tabRestaurant.newTab().setText("버거"))
        binding.tabRestaurant.addTab( binding.tabRestaurant.newTab().setText("스낵"))
        binding.tabRestaurant.addTab( binding.tabRestaurant.newTab().setText("음료"))

        binding.tabRestaurant.tabMode = MODE_SCROLLABLE
        binding.tabRestaurant.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (binding.tabRestaurant.selectedTabPosition) {
                    0 -> {
                        //binding.nestedScrollRestaurant.scrollTo(0,binding.tabRestaurant.top)
                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                    4 -> {

                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


    }
}

