package com.yoonji.coupangeatsproject.src.main.home

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.src.address.AddressActivity
import com.yoonji.coupangeatsproject.src.main.home.adapter.ChooseRestaurantAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FoodTypeAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FranchaiseAdapter
import com.yoonji.coupangeatsproject.src.main.home.data.ChooseRestaurantData
import com.yoonji.coupangeatsproject.src.main.home.data.FoodTypeData
import com.yoonji.coupangeatsproject.src.main.home.data.FranchaiseData
import com.yoonji.coupangeatsproject.src.main.home.models.MainLoginResponse
import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse


class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {

    private var TAG = "**HomeFragment--->"
    val token = ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, "")

    private var images = mutableListOf<String>()

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

        initAdSlider()

        val userIdx = arguments?.getInt("userIdx")

        if(token == "")
            HomeService(this).getNotLoginMain()
        else {
            if (userIdx != null) {
                HomeService(this).getLoginMain(userIdx)
            }
        }
    }

    private fun initAdSlider(){
//        Glide.with(requireContext()).load(images[0]).into(binding.imgvHomeAdOne)
//        Glide.with(requireContext()).load(images[1]).into(binding.imgvHomeAdTwo)

        Glide.with(requireContext()).load("https://prod.rising-eve.shop/img/banner1.png").into(binding.imgvHomeAdOne)
        Glide.with(requireContext()).load("https://prod.rising-eve.shop/img/banner2.png").into(binding.imgvHomeAdTwo)

        binding.flipperAd.flipInterval = 2000
        binding.flipperAd.isAutoStart = true

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

    override fun onGetMainNotLoginSuccess(response: MainResponse) {
        Log.d(TAG, "onGetMainSuccess: $response")

        // 배너광고
        for(image in response.result.bannerResult) {
            images.apply {
                add(image.bannerImg)
            }
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
            if(value.starRating == null && value.coupon == null ){          // 리뷰 X, 쿠폰 X
                chooseRestaurantDatas.apply {
                    add(
                        ChooseRestaurantData(
                            restaurantMainImg = value.storeImg,
                            restaurantUpImg = value.storeImg,
                            restaurantDownImg = value.storeImg,
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
            } else if(value.starRating == null){            // 리뷰 X
                chooseRestaurantDatas.apply {
                    add(
                        ChooseRestaurantData(
                            restaurantMainImg = value.storeImg,
                            restaurantUpImg = value.storeImg,
                            restaurantDownImg = value.storeImg,
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
            } else if(value.coupon == null) {       // 쿠폰 X
                chooseRestaurantDatas.apply {
                    add(
                        ChooseRestaurantData(
                            restaurantMainImg = value.storeImg,
                            restaurantUpImg = value.storeImg,
                            restaurantDownImg = value.storeImg,
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
                            restaurantUpImg = value.storeImg,
                            restaurantDownImg = value.storeImg,
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
        //initAdSlider()
        initFranchaiseRecycler()
        initChooseRestaurantRecycler()
    }

    override fun onGetMainNotLoginFailure(message: String) {
        Log.d(TAG, "onGetMainFailure: $message")
    }

    override fun onGetMainLoginSuccess(response: MainLoginResponse) {
        Log.d(TAG, "onGetMainLoginSuccess: $response")

        if(response.isSuccess){
            // 배너광고
            for(image in response.result.bannerResult) {
                images.apply {
                    add(image.bannerImg)
                }
            }

            // 카테고리
            for ((index,value) in response.result.categoryResult.withIndex()){
                foodTypeDatas.apply {
                    add(FoodTypeData(typeImg = value.categoryImg,typeTitle = value.categoryName))
                }
            }

            // 프랜차이즈
            for ((index,value) in response.result.inFranchiseResult.withIndex()) {
                if(value.starRating == null){
                    franchaiseDatas.apply {
                        add(
                            FranchaiseData(
                                franchaiseImg = value.storeImg,
                                franchaiseTitle = value.name,
                                franchaiseReview = "",
                                franchaiseDistance = value.dist,
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
                                franchaiseDistance = value.dist,
                                franchaiseDeliveryFee = value.coupon_or_deliveryFee
                            )
                        )
                    }
                }
            }

            // 골라먹는 맛집
            for ((index,value) in response.result.inStoreListResult.withIndex()) {
                if(value.starRating == null && value.coupon == null ){
                    chooseRestaurantDatas.apply {
                        add(
                            ChooseRestaurantData(
                                restaurantMainImg = value.storeImg,
                                restaurantUpImg = value.storeImg,      //temporary
                                restaurantDownImg = value.storeImg,       //temporary
                                restaurantName = value.name,
                                restaurantReview = "",
                                restaurantDistance = value.dist,
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
                                restaurantDistance = value.dist,
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
                                restaurantDistance = value.dist,
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
                                restaurantDistance = value.dist,
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
            //initAdSlider()
            initFranchaiseRecycler()
            initChooseRestaurantRecycler()
        }else{
            Log.d(TAG, "msg: " + response.message)

        }

    }

    override fun onGetMainLoginFailure(message: String) {
        Log.d(TAG, "onGetMainLoginFailure: $message")
    }


}

