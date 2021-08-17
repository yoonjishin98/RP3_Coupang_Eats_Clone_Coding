package com.yoonji.coupangeatsproject.src.main.home

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.src.main.home.adapter.FoodTypeAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FranchaiseAdapter
import com.yoonji.coupangeatsproject.src.main.home.models.FoodTypeData
import com.yoonji.coupangeatsproject.src.main.home.models.FranchaiseData

class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {

    private var images:Array<Int> = arrayOf(R.drawable.img_ad_one,R.drawable.img_ad_two)

    private lateinit var foodTypeAdater : FoodTypeAdapter
    private var foodTypeDatas = mutableListOf<FoodTypeData>()

    private lateinit var franchaiseAdapter : FranchaiseAdapter
    private var franchaiseDatas = mutableListOf<FranchaiseData>()


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

    }

}