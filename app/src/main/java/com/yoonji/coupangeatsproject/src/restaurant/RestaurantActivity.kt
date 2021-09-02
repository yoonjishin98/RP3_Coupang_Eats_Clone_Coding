package com.yoonji.coupangeatsproject.src.restaurant

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityRestaurantBinding
import com.yoonji.coupangeatsproject.src.category.CategoryService
import com.yoonji.coupangeatsproject.src.main.home.models.storeMenuByCateResult
import com.yoonji.coupangeatsproject.src.order_cart.OrderCartActivity
import com.yoonji.coupangeatsproject.src.restaurant.adapter.RestaurantMenuAdapter
import com.yoonji.coupangeatsproject.src.restaurant.adapter.RestaurantReviewAdapter
import com.yoonji.coupangeatsproject.src.restaurant.data.RestaurantDetailData
import com.yoonji.coupangeatsproject.src.restaurant.data.RestaurantMenuData
import com.yoonji.coupangeatsproject.src.restaurant.data.RestaurantReviewData
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantResponse


class RestaurantActivity : BaseActivity<ActivityRestaurantBinding>(ActivityRestaurantBinding::inflate), RestaurantActivityView{

    private val TAG = "*****RestaurantActivity------>"

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
        setSupportActionBar(toolbar)

        //상태바 투명
        window?.decorView?.systemUiVisibility =
            SYSTEM_UI_FLAG_LAYOUT_STABLE or SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        window.statusBarColor = Color.TRANSPARENT



        // 카트에 담은 후 넘어오면 or 이미 카트에 이미 담아놓은 메뉴들이 있으면 최하단 버튼 보이도록
        val count = intent.getIntExtra("cartCount", 0)
        val price = intent.getIntExtra("cartPrice", 0)
        val check = intent.getStringExtra("cartCheck")
        totalCount += count
        totalPrice += price

        editor.putInt("RestaurantCount", totalCount)        //레스토랑 개수와 가격을 sharedPreference 이용해서 저장
        editor.putInt("RestaurantPrice", totalPrice)
        editor.apply()

        // 결제하기 화면으로 이동
        if(check == "letsOrder" || totalCount>0){
            binding.layoutRestaurantCart.visibility = VISIBLE
            binding.tvRestaurantTotalCount.text = totalCount.toString()
            binding.tvRestaurantTotalPrice.text = totalPrice.toString() + "원"

            binding.layoutRestaurantCart.setOnClickListener{
                val intent = Intent(this, OrderCartActivity::class.java)
                startActivity(intent)
            }
        }

        // 음식점 상세화면 api
        RestaurantService(this).getRestaurantDetail(1)
    }

    fun initReviewRecycler(){
        reviewAdapter = RestaurantReviewAdapter(this)
        binding.rvRestaurantReview.adapter = reviewAdapter

        reviewAdapter.datas = reviewDatas
    }

    fun initMenuRecycler(){
        menuAdapter = RestaurantMenuAdapter(this)
        binding.rvRestaurantMenu.adapter = menuAdapter

        menuAdapter.datas = menuDatas
    }


    override fun onResume() {
        super.onResume()

        binding.imgvRestaurantBack.setOnClickListener { finish()}
    }

    override fun onGetRestaurantSuccess(response: RestaurantResponse) {
        Log.d(TAG, "onGetRestaurantSuccess: 성공 "+ response.result)

        if(response.isSuccess){

            // 툴바 접혀졌을 때 여부에 따른 아이콘 및 타이틀 변화
            var isShow = true
            var scrollRange = -1
            binding.appBarRestaurant.addOnOffsetChangedListener( AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (scrollRange == -1){
                    scrollRange = appBarLayout.totalScrollRange
                }

                if (scrollRange + verticalOffset == 0){     //appbar가 접혔을 때, 툴바에 보이도록
                    binding.tvRestaurantToolbarTitle.visibility = VISIBLE
                    binding.tvRestaurantToolbarTitle.text = response.result.storeInfoResult[0].name
                    binding.imgvRestaurantBack.imageTintList = ColorStateList.valueOf(this.getColor(R.color.black))
                    binding.imgvRestaurantHeart.imageTintList = ColorStateList.valueOf(this.getColor(R.color.pinkForLike))
                    binding.imgvRestaurantShare.imageTintList = ColorStateList.valueOf(this.getColor(R.color.black))

                    isShow = true

                } else if (isShow){     //appbar가 펼쳐졌을 때
                    binding.tvRestaurantToolbarTitle.visibility = INVISIBLE
                    binding.imgvRestaurantBack.imageTintList = ColorStateList.valueOf(this.getColor(R.color.white))
                    binding.imgvRestaurantHeart.imageTintList = ColorStateList.valueOf(this.getColor(R.color.white))
                    binding.imgvRestaurantShare.imageTintList = ColorStateList.valueOf(this.getColor(R.color.white))

                    isShow = false
                }
            } )

            //상세 정보
            var info = response.result.storeInfoResult[0]
            Glide.with(this).load(response.result.storeImgResult[0].storeImg).into(binding.imgvRestaurantMain)
            binding.tvRestaurantTitle.text = info.name
            binding.tvRestaurantToolbarTitle.text = info.name
            binding.tvRestaurantDeliveryTime.text = info.deliveryTime
            if(info.starRating == null) {
                binding.imgvRestaurantStar.visibility = GONE
                binding.tvRestaurantReviewScore.visibility = GONE
            }else{
                binding.imgvRestaurantStar.visibility = VISIBLE
                binding.tvRestaurantReviewScore.text = info.starRating.toString()
            }
            if(info.fastDelivery == "Y")
                binding.imgvRestaurantCheetah.visibility = VISIBLE
            else
                binding.imgvRestaurantCheetah.visibility = GONE
            binding.tvRestaurantDeliveryFee.text = info.deliveryFee
            binding.tvRestaurantMinimumOrder.text = info.minOrderPrice


            //탭 레이아웃
            for(i in response.result.storeMenuByCate){
                binding.tabRestaurant.addTab( binding.tabRestaurant.newTab().setText(i.menuCategory))
                Log.d(TAG, "tab 생성")
            }
            binding.tabRestaurant.tabMode = MODE_SCROLLABLE
            binding.tabRestaurant.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    TODO("스크롤")
//                    if(binding.tabRestaurant.selectedTabPosition == )
                }
                override fun onTabUnselected(tab: TabLayout.Tab) {}
                override fun onTabReselected(tab: TabLayout.Tab) {}
            })


            //리뷰
            for((index,value) in response.result.storePtReviewResult.withIndex()){
                if(value.reviewImg == null){
                    reviewDatas.add( RestaurantReviewData(
                        reviewImg = "",
                        reviewTitle = value.content,
                        reviewStarScore = value.starRating) )
                }else{
                    reviewDatas.add( RestaurantReviewData(
                        reviewImg = value.reviewImg,
                        reviewTitle = value.content,
                        reviewStarScore = value.starRating) )
                }

            }

//            reviewDatas.apply{
//                add(RestaurantReviewData(
//                    reviewImg = R.drawable.img_restaurant_review,
//                    reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히",
//                    reviewStarScore = 3))
//                add(RestaurantReviewData(
//                    reviewImg = R.drawable.img_restaurant_review,
//                    reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히dfdasfasfsfsfasfsdfsdfsf",
//                    reviewStarScore = 5))
//                add(RestaurantReviewData(
//                    reviewImg = R.drawable.img_restaurant_review,
//                    reviewTitle = "너무너무 맛있어요~ 감사합니다 히히히히",
//                    reviewStarScore = 1))
//            }

            //메뉴
            var storeMenu = response.result.storeMenuByCate

            for((index,value) in storeMenu.withIndex()){
                var menu = storeMenu[index].menu

                for(i in menu){
                    if(i.menuImg == null && i.menuInfo == null){
                        menuDetailDatas.apply {
                            add( RestaurantDetailData(
                                restaurantDetailImg = "",
                                restaurantDetailDescrip = "",
                                restaurantDetailPrice = i.price,
                                restaurantDetailTitle = i.name) )
                        }
                    }else if(i.menuImg == null){
                        menuDetailDatas.apply {
                            add( RestaurantDetailData(
                                restaurantDetailImg = "",
                                restaurantDetailDescrip = i.menuInfo,
                                restaurantDetailPrice = i.price,
                                restaurantDetailTitle = i.name) )
                        }
                    }else if(i.menuInfo == null){
                        menuDetailDatas.apply {
                            add( RestaurantDetailData(
                                restaurantDetailImg = i.menuImg,
                                restaurantDetailDescrip = "",
                                restaurantDetailPrice = i.price,
                                restaurantDetailTitle = i.name) )
                        }
                    }else{
                        menuDetailDatas.apply {
                            add( RestaurantDetailData(
                                restaurantDetailImg = i.menuImg,
                                restaurantDetailDescrip = i.menuInfo,
                                restaurantDetailPrice = i.price,
                                restaurantDetailTitle = i.name) )
                        }
                    }
                }

                menuDatas.apply{
                    add(RestaurantMenuData(restaurantMenuTitle = value.menuCategory, menuDetailArrayList = menuDetailDatas))
                }
            }

            initReviewRecycler()
            initMenuRecycler()

        }else{
            Log.d(TAG, "response is false")
        }
    }

    override fun onGetRestaurantFailure(message: String) {
        Log.d(TAG, "onGetRestaurantFailure: $message")
    }
}

