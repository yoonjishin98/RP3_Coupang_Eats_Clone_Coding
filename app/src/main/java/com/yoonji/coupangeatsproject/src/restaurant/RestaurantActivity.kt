package com.yoonji.coupangeatsproject.src.restaurant

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.VISIBLE
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityRestaurantBinding
import com.yoonji.coupangeatsproject.src.order_cart.OrderCartActivity
import com.yoonji.coupangeatsproject.src.restaurant.adapter.RestaurantMenuAdapter
import com.yoonji.coupangeatsproject.src.restaurant.adapter.RestaurantReviewAdapter
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantDetailData
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantMenuData
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantReviewData


class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>(ActivityRestaurantBinding::inflate) {

    lateinit var reviewAdapter : RestaurantReviewAdapter
    val reviewDatas = mutableListOf<RestaurantReviewData>()

    lateinit var menuAdapter : RestaurantMenuAdapter
    var menuDatas = mutableListOf<RestaurantMenuData>()
    var menuDetailDatas = mutableListOf<RestaurantDetailData>()

    var totalCount = ApplicationClass.sSharedPreferences.getInt("RestaurantCount",0)
    var totalPrice = ApplicationClass.sSharedPreferences.getInt("RestaurantPrice",0)

    val editor: SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val toolbar = findViewById<View>(R.id.toolbar_restaurant) as androidx.appcompat.widget.Toolbar
        toolbar.title = "KFC 왕십리역사점"
        setSupportActionBar(toolbar)

        //툴바에 뒤로 가기
        setSupportActionBar(binding.toolbarRestaurant)
        if (supportActionBar != null)
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initReviewRecycler()
        initMenuRecycler()

        // 카트에 담은 후 넘어오면 + 이미 카트에 담아놓은 메뉴들이 있으면 최하단 버튼 보이도록
        val count = intent.getIntExtra("cartCount", 0)
        val price = intent.getIntExtra("cartPrice", 0)
        val check = intent.getStringExtra("cartCheck")
        totalCount += count
        totalPrice += price
        editor.putInt("RestaurantCount", totalCount)
        editor.putInt("RestaurantPrice", totalPrice)
        editor.apply()

        Log.d("TAG", "totalCount: $totalCount")
        Log.d("TAG", "totalPrice: $totalPrice")

        if(check == "1000" || totalCount>0){
            binding.layoutRestaurantCart.visibility = VISIBLE
            binding.tvRestaurantTotalCount.text = totalCount.toString()
            binding.tvRestaurantTotalPrice.text = totalPrice.toString() + "원"

            binding.layoutRestaurantCart.setOnClickListener{
                val intent = Intent(this, OrderCartActivity::class.java)
                startActivity(intent)
            }
        }

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


    fun initReviewRecycler(){
        reviewAdapter = RestaurantReviewAdapter(this)
        binding.rvRestaurantReview.adapter = reviewAdapter

        reviewDatas.apply{
            add(
                RestaurantReviewData(reviewImg = R.drawable.img_restaurant_review,reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히",
                reviewStarScore = 3)
            )
            add(
                RestaurantReviewData(reviewImg = R.drawable.img_restaurant_review,reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히dfdasfasfsfsfasfsdfsdfsf",
                reviewStarScore = 5)
            )
            add(
                RestaurantReviewData(reviewImg = R.drawable.img_restaurant_review,reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히",
                reviewStarScore = 1)
            )
        }
        reviewAdapter.datas = reviewDatas
    }

    fun initMenuRecycler(){
        menuAdapter = RestaurantMenuAdapter(this)
        binding.rvRestaurantMenu.adapter = menuAdapter

        menuDetailDatas.apply{
            add(RestaurantDetailData(restaurantDetailImg = R.drawable.img_restaurant_menu,restaurantDetailDescrip = "고구마무스와 모짜렐라 치즈와의 완벽한 조합",
                restaurantDetailPrice = "30000",restaurantDetailTitle = "어쩌구 저쩌구 피자"))
            add(RestaurantDetailData(restaurantDetailImg = R.drawable.img_restaurant_menu,restaurantDetailDescrip = "고구마무스와 모짜렐라 치즈와의 완벽한 조합",
                restaurantDetailPrice = "29000",restaurantDetailTitle = "어쩌구 저쩌구 피자"))
        }

        menuDatas.apply {
            add(RestaurantMenuData(restaurantMenuTitle = "베스트메뉴", menuDetailArrayList = menuDetailDatas))
            add(RestaurantMenuData(restaurantMenuTitle = "추천메뉴", menuDetailArrayList = menuDetailDatas))
            add(RestaurantMenuData(restaurantMenuTitle = "사이드", menuDetailArrayList = menuDetailDatas ))
        }
        menuAdapter.datas = menuDatas
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

