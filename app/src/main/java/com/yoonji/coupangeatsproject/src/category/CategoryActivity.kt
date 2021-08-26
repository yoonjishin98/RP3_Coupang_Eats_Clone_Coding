package com.yoonji.coupangeatsproject.src

import android.os.Bundle
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityCategoryBinding
import com.yoonji.coupangeatsproject.databinding.ActivityMainBinding
import com.yoonji.coupangeatsproject.src.category.adapter.CategoryFoodTypeAdapter
import com.yoonji.coupangeatsproject.src.category.adapter.CategoryRestaurantAdapter
import com.yoonji.coupangeatsproject.src.category.data.CategoryFoodTypeData
import com.yoonji.coupangeatsproject.src.category.data.CategoryRestaurantData
import com.yoonji.coupangeatsproject.src.main.home.adapter.ChooseRestaurantAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FoodTypeAdapter
import com.yoonji.coupangeatsproject.src.main.home.data.ChooseRestaurantData
import com.yoonji.coupangeatsproject.src.main.home.data.FoodTypeData

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate) {

    private var TAG = "**CategoryActivity--->"

    private lateinit var categoryFoodTypeAdater : CategoryFoodTypeAdapter
    private var categoryFoodTypeDatas = mutableListOf<CategoryFoodTypeData>()

    private lateinit var categoryRestaurantAdapter : CategoryRestaurantAdapter
    private var categoryRestaurantDatas = mutableListOf<CategoryRestaurantData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    private fun initCategoryFoodTypeRecycler() {
        categoryFoodTypeAdater = CategoryFoodTypeAdapter(this@CategoryActivity)
        binding.rvCategory.adapter = categoryFoodTypeAdater

        categoryFoodTypeAdater.datas = categoryFoodTypeDatas
    }

    private fun initCategoryRestaurantRecycler(){
        categoryRestaurantAdapter = CategoryRestaurantAdapter(this@CategoryActivity)
        binding.rvHomeChooseRestaurant.adapter = categoryRestaurantAdapter

        categoryRestaurantAdapter.datas = categoryRestaurantDatas
    }

}