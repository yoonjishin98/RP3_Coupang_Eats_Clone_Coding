package com.yoonji.coupangeatsproject.src.main.home

import android.graphics.Rect
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.js.utils.DimensionUtils
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.src.main.home.adapter.AdSlideAdapater
import com.yoonji.coupangeatsproject.src.main.home.adapter.FoodTypeAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FranchaiseAdapter
import com.yoonji.coupangeatsproject.src.main.home.models.FoodTypeData
import com.yoonji.coupangeatsproject.src.main.home.models.FranchaiseData

class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),
    HomeFragmentView {

    private val sliderHandler: Handler = Handler()
    private var sliderItems = mutableListOf<String>()
    private lateinit var slideAdpater : AdSlideAdapater

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


        initAdViewPager()
        initFoodTypeRecycler()
        initFranchaiseRecycler()


    }

    private fun initAdViewPager(){
        sliderItems.apply {
            add("https://cdn.pixabay.com/photo/2019/12/26/10/44/horse-4720178_1280.jpg")
            add("https://cdn.pixabay.com/photo/2020/11/04/15/29/coffee-beans-5712780_1280.jpg")
        }

        binding.vpHome.adapter =AdSlideAdapater(requireContext(),sliderItems)
    }

    private fun initFoodTypeRecycler() {
        foodTypeAdater = FoodTypeAdapter(requireContext())
        binding.rvHomeFoodType.adapter = foodTypeAdater

        foodTypeDatas.apply {
            add (FoodTypeData(typeImg = R.drawable.img_food_type_two, typeTitle = "1인분"))
            add (FoodTypeData(typeImg = R.drawable.img_food_type_one, typeTitle = "한식"))
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
        }

        franchaiseAdapter.fdatas = franchaiseDatas
    }

}