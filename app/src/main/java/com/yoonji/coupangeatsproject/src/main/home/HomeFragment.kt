package com.yoonji.coupangeatsproject.src.main.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.src.address.AddressActivity
import com.yoonji.coupangeatsproject.src.main.MainActivity
import com.yoonji.coupangeatsproject.src.main.home.adapter.ChooseRestaurantAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FoodTypeAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FranchaiseAdapter
import com.yoonji.coupangeatsproject.src.main.home.data.ChooseRestaurantData
import com.yoonji.coupangeatsproject.src.main.home.data.FoodTypeData
import com.yoonji.coupangeatsproject.src.main.home.data.FranchaiseData
import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse


class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {

    private var TAG = "**HomeFragment--->"
    val token = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, "")
    lateinit var mContext: Context

    private var images = mutableListOf<Int>()

    private lateinit var foodTypeAdater : FoodTypeAdapter
    private var foodTypeDatas = mutableListOf<FoodTypeData>()

    private lateinit var franchaiseAdapter : FranchaiseAdapter
    private var franchaiseDatas = mutableListOf<FranchaiseData>()

    private lateinit var restaurantAdapter : ChooseRestaurantAdapter
    private var chooseRestaurantDatas = mutableListOf<ChooseRestaurantData>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarHome.inflateMenu(R.menu.home_toolbar_menu)
        binding.toolbarHome.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.home_menu_search -> {

                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }

        if(token == "")
            HomeService(this).getNotLoginMain()
        else {
            //HomeService(this).getLoginMain()
        }

    }

    private fun initAdSlider(){
        for ((index, value) in images.withIndex()){
            val image = ImageView(requireContext())
            Glide.with(this).load(value).into(image)

            binding.flipperAd.addView(image)
            binding.flipperAd.flipInterval = 2000
            binding.flipperAd.isAutoStart = true
        }
    }

    private fun initFoodTypeRecycler() {
        foodTypeAdater = FoodTypeAdapter(ApplicationClass.applicationContext())
        binding.rvHomeFoodType.adapter = foodTypeAdater

        foodTypeAdater.datas = foodTypeDatas
    }

    private fun initFranchaiseRecycler() {
        franchaiseAdapter = FranchaiseAdapter(requireContext())
        binding.rvHomeFranchise.adapter = franchaiseAdapter

        franchaiseAdapter.fdatas = franchaiseDatas
    }

    private fun initChooseRestaurantRecycler(){
        restaurantAdapter = ChooseRestaurantAdapter(requireContext())
        binding.rvHomeChooseRestaurant.adapter = restaurantAdapter

        restaurantAdapter.datas = chooseRestaurantDatas
    }

    override fun onAttach(context: Context) {       //프래그먼트가 액티비티와 연결되어 있었던 경우 액티비티는 여기로 호출
        super.onAttach(context)
        mContext = context
    }


    override fun onResume() {
        super.onResume()
        binding.imgvHomeLoca.setOnClickListener{
            val intent = Intent(requireContext(), AddressActivity::class.java)
            startActivity(intent)
        }
        binding.imgvHomeAddressArrow.setOnClickListener{
            val intent = Intent(requireContext(), AddressActivity::class.java)
            startActivity(intent)
        }
        binding.tvHomeAddress.setOnClickListener{
            val intent = Intent(requireContext(), AddressActivity::class.java)
            startActivity(intent)
        }

        binding.temporary.setOnClickListener {
            val editor: SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
            editor.putInt("RestaurantCount", 0)
            editor.putInt("RestaurantPrice", 0)
            editor.apply()
        }

    }

    override fun onGetMainSuccess(response: MainResponse) {
        Log.d(TAG, "onGetMainSuccess: $response")

        // 배너광고
        images.apply {
            //add(value.bannerImg)
            add(R.drawable.img_ad_one)
            add(R.drawable.img_ad_two)
        }

        // 카테고리
        for ((index,value) in response.result.categoryResult.withIndex()){
            foodTypeDatas.apply {
                add(FoodTypeData(typeImg = value.categoryImg,typeTitle = value.categoryName))
            }
        }

        // 프랜차이즈
        for ((index,value) in response.result.franchiseResult.withIndex()) {
            if(value.starRating == null){
                franchaiseDatas.apply {
                    add(
                        FranchaiseData(
                            franchaiseImg = value.storeImg,
                            franchaiseTitle = value.name,
                            franchaiseReview = "",
                            franchaiseDistance = "",
                            franchaiseDeliveryFee = value.coupon_or_deliveryFee
                        )
                    )
                }
            }else {
                franchaiseDatas.apply {
                    add(
                        FranchaiseData(
                            franchaiseImg = value.storeImg,
                            franchaiseTitle = value.name,
                            franchaiseReview = value.starRating + " (" + value.reviewCount.toString() + ")",
                            franchaiseDistance = "",
                            franchaiseDeliveryFee = value.coupon_or_deliveryFee
                        )
                    )
                }
            }
        }

        // 골라먹는 맛집
        for ((index,value) in response.result.storeListResult.withIndex()) {
            if(value.starRating == null && value.coupon == null ){
                chooseRestaurantDatas.apply {
                    add(
                        ChooseRestaurantData(
                            restaurantMainImg = value.storeImg,
                            restaurantUpImg = value.storeImg,      //temporary
                            restaurantDownImg = value.storeImg,       //temporary
                            restaurantName = value.name,
                            restaurantReview = "",
                            restaurantDistance = "",
                            restaurantDeliveryFee = value.deliveryFee,
                            restaurantDeliveryTime = value.deliveryTime,
                            restaurantCheetahDelivery = value.fastDelivery,
                            restaurantCoupon = ""
                        )
                    )
                }
            } else if(value.starRating == null){
                chooseRestaurantDatas.apply {
                    add(
                        ChooseRestaurantData(
                            restaurantMainImg = value.storeImg,
                            restaurantUpImg = value.storeImg,      //temporary
                            restaurantDownImg = value.storeImg,       //temporary
                            restaurantName = value.name,
                            restaurantReview = "",
                            restaurantDistance = "",
                            restaurantDeliveryFee = value.deliveryFee,
                            restaurantDeliveryTime = value.deliveryTime,
                            restaurantCheetahDelivery = value.fastDelivery,
                            restaurantCoupon = value.coupon
                        )
                    )
                }
            } else if(value.coupon == null) {
                chooseRestaurantDatas.apply {
                    add(
                        ChooseRestaurantData(
                            restaurantMainImg = value.storeImg,
                            restaurantUpImg = value.storeImg,      //temporary
                            restaurantDownImg = value.storeImg,       //temporary
                            restaurantName = value.name,
                            restaurantReview = value.starRating + " (" + value.reviewCount.toString() + ")",
                            restaurantDistance = "",
                            restaurantDeliveryFee = value.deliveryFee,
                            restaurantDeliveryTime = value.deliveryTime,
                            restaurantCheetahDelivery = value.fastDelivery,
                            restaurantCoupon = ""
                        )
                    )
                }
            } else {
                chooseRestaurantDatas.apply {
                    add(
                        ChooseRestaurantData(
                            restaurantMainImg = value.storeImg,
                            restaurantUpImg = value.storeImg,      //temporary
                            restaurantDownImg = value.storeImg,       //temporary
                            restaurantName = value.name,
                            restaurantReview = value.starRating + " (" + value.reviewCount.toString() + ")",
                            restaurantDistance = "",
                            restaurantDeliveryFee = value.deliveryFee,
                            restaurantDeliveryTime = value.deliveryTime,
                            restaurantCheetahDelivery = value.fastDelivery,
                            restaurantCoupon = value.coupon
                        )
                    )
                }
            }
        }


        initFoodTypeRecycler()
        initAdSlider()
        initFranchaiseRecycler()
        initChooseRestaurantRecycler()
    }

    override fun onGetMainFailure(message: String) {
        Log.d(TAG, "onGetMainFailure: $message")
    }


}

