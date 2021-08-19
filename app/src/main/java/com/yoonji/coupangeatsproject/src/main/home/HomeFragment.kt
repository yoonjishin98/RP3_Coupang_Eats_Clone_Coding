package com.yoonji.coupangeatsproject.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.src.AddressActivity
import com.yoonji.coupangeatsproject.src.main.home.adapter.ChooseRestaurantAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FoodTypeAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FranchaiseAdapter
import com.yoonji.coupangeatsproject.src.main.home.models.ChooseRestaurantData
import com.yoonji.coupangeatsproject.src.main.home.models.FoodTypeData
import com.yoonji.coupangeatsproject.src.main.home.models.FranchaiseData

class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {

    private var images:Array<Int> = arrayOf(R.drawable.img_ad_one,R.drawable.img_ad_two)

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
        initFoodTypeRecycler()
        initFranchaiseRecycler()
        initChooseRestaurantRecycler()

    }

    private fun initAdSlider(){
        for (i in images){
            val image:ImageView = ImageView(requireContext())
            image.setBackgroundResource(i)

            binding.flipperAd.addView(image)
            binding.flipperAd.flipInterval = 5000
            binding.flipperAd.isAutoStart = true
        }
    }

    private fun initFoodTypeRecycler() {
        foodTypeAdater = FoodTypeAdapter(requireContext())
        binding.rvHomeFoodType.adapter = foodTypeAdater

        foodTypeDatas.apply {
            add (FoodTypeData(typeImg = R.drawable.img_food_type_two, typeTitle = "1인분"))
            add (FoodTypeData(typeImg = R.drawable.img_food_type_one, typeTitle = "한식"))
            add (FoodTypeData(typeImg = R.drawable.img_food_type_one, typeTitle = "종류"))
            add (FoodTypeData(typeImg = R.drawable.img_food_type_one, typeTitle = "종류"))
            add (FoodTypeData(typeImg = R.drawable.img_food_type_one, typeTitle = "종류"))
            add (FoodTypeData(typeImg = R.drawable.img_food_type_one, typeTitle = "종류"))
        }
        foodTypeAdater.datas = foodTypeDatas
    }

    private fun initFranchaiseRecycler() {
        franchaiseAdapter = FranchaiseAdapter(requireContext())
        binding.rvHomeFranchise.adapter = franchaiseAdapter

        franchaiseDatas.apply {
            add(
                FranchaiseData(
                    franchaiseImg = R.drawable.img_franchaise_one,
                    franchaiseTitle = "가마로강정 행당역점",
                    franchaiseReview = "4.9(56)",
                    franchaiseDistance = "1.8",
                    franchaiseDeliveryFee = 0
                )
            )
            add(
                FranchaiseData(
                    franchaiseImg = R.drawable.img_franchaise_two,
                    franchaiseTitle = "돼지게티 신당왕십리점",
                    franchaiseReview = "4.5(58)",
                    franchaiseDistance = "0.9",
                    franchaiseDeliveryFee = 1500
                )
            )
            add(
                FranchaiseData(
                    franchaiseImg = R.drawable.img_franchaise_two,
                    franchaiseTitle = "돼지게티 신당왕십리점",
                    franchaiseReview = "4.5(58)",
                    franchaiseDistance = "0.9",
                    franchaiseDeliveryFee = 1500
                )
            )
            add(
                FranchaiseData(
                    franchaiseImg = R.drawable.img_franchaise_two,
                    franchaiseTitle = "돼지게티 신당왕십리점",
                    franchaiseReview = "4.5(58)",
                    franchaiseDistance = "0.9",
                    franchaiseDeliveryFee = 1500
                )
            )
            add(
                FranchaiseData(
                    franchaiseImg = R.drawable.img_franchaise_two,
                    franchaiseTitle = "돼지게티 신당왕십리점",
                    franchaiseReview = "4.5(58)",
                    franchaiseDistance = "0.9",
                    franchaiseDeliveryFee = 1500
                )
            )
        }
        franchaiseAdapter.fdatas = franchaiseDatas
    }

    private fun initChooseRestaurantRecycler(){
        restaurantAdapter = ChooseRestaurantAdapter(requireContext())
        binding.rvHomeChooseRestaurant.adapter = restaurantAdapter

        chooseRestaurantDatas.apply {
            add(
                ChooseRestaurantData(
                    restaurantMainImg = R.drawable.img_choose_one_o,
                    restaurantUpImg = R.drawable.img_choose_one_t,
                    restaurantDownImg = R.drawable.img_choose_one_th,
                    restaurantName = "맥도날드 한양대점",
                    restaurantReview = "0",
                    restaurantDistance = "1.2",
                    restaurantDeliveryFee = "1500",
                    restaurantDeliveryTime = "25~30분",
                    restaurantCheetahDelivery = R.drawable.ic_cheetah_delivery,
                    restaurantCoupon = "2,000원 쿠폰"
                )
            )
            add(
                ChooseRestaurantData(
                    restaurantMainImg = R.drawable.img_choose_one_o,
                    restaurantUpImg = R.drawable.img_choose_one_t,
                    restaurantDownImg = R.drawable.img_choose_one_th,
                    restaurantName = "맥도날드 한양대점",
                    restaurantReview = "4.1(1)",
                    restaurantDistance = "1.2",
                    restaurantDeliveryFee = "1500",
                    restaurantDeliveryTime = "25~30분",
                    restaurantCheetahDelivery = R.drawable.ic_cheetah_delivery,
                    restaurantCoupon = "1,000원 쿠폰"
                )
            )
            add(
                ChooseRestaurantData(
                    restaurantMainImg = R.drawable.img_choose_one_o,
                    restaurantUpImg = R.drawable.img_choose_one_t,
                    restaurantDownImg = R.drawable.img_choose_one_th,
                    restaurantName = "맥도날드 한양대점",
                    restaurantReview = "0",
                    restaurantDistance = "1.2",
                    restaurantDeliveryFee = "1,500",
                    restaurantDeliveryTime = "25~30분",
                    restaurantCheetahDelivery = 0,
                    restaurantCoupon = "0"
                )
            )
            add(
                ChooseRestaurantData(
                    restaurantMainImg = R.drawable.img_choose_one_o,
                    restaurantUpImg = R.drawable.img_choose_one_t,
                    restaurantDownImg = R.drawable.img_choose_one_th,
                    restaurantName = "맥도날드 한양대점",
                    restaurantReview = "4.1(1)",
                    restaurantDistance = "1.2",
                    restaurantDeliveryFee = "1500",
                    restaurantDeliveryTime = "25~30분",
                    restaurantCheetahDelivery = 0,
                    restaurantCoupon = "3,000원 쿠폰"
                )
            )


        }
        restaurantAdapter.datas = chooseRestaurantDatas

        Log.d("TAG", "initChooseRestaurantRecycler: " + restaurantAdapter.itemCount)
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
    }

}