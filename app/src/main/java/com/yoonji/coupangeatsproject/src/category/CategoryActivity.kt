package com.yoonji.coupangeatsproject.src

import android.os.Bundle
import android.util.Log
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityCategoryBinding
import com.yoonji.coupangeatsproject.databinding.ActivityMainBinding
import com.yoonji.coupangeatsproject.src.category.CategoryActivityView
import com.yoonji.coupangeatsproject.src.category.CategoryService
import com.yoonji.coupangeatsproject.src.category.adapter.CategoryFoodTypeAdapter
import com.yoonji.coupangeatsproject.src.category.adapter.CategoryRestaurantAdapter
import com.yoonji.coupangeatsproject.src.category.data.CategoryFoodTypeData
import com.yoonji.coupangeatsproject.src.category.data.CategoryRestaurantData
import com.yoonji.coupangeatsproject.src.category.models.CategoryResponse
import com.yoonji.coupangeatsproject.src.main.home.HomeService
import com.yoonji.coupangeatsproject.src.main.home.adapter.ChooseRestaurantAdapter
import com.yoonji.coupangeatsproject.src.main.home.adapter.FoodTypeAdapter
import com.yoonji.coupangeatsproject.src.main.home.data.ChooseRestaurantData
import com.yoonji.coupangeatsproject.src.main.home.data.FoodTypeData

class CategoryActivity : BaseActivity<ActivityCategoryBinding>(ActivityCategoryBinding::inflate)
    ,CategoryActivityView {

    private var TAG = "**CategoryActivity--->"

    private lateinit var categoryFoodTypeAdater : CategoryFoodTypeAdapter
    private var categoryFoodTypeDatas = mutableListOf<CategoryFoodTypeData>()

    private lateinit var categoryRestaurantAdapter : CategoryRestaurantAdapter
    private var categoryRestaurantDatas = mutableListOf<CategoryRestaurantData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CategoryService(this).getCategory(5,3)      //임시로 userIdx 넣어놓음

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




    override fun onGetCategorySuccess(response: CategoryResponse) {

        // 카테고리
        for ((index,value) in response.result.categoryResult.withIndex()){
            categoryFoodTypeDatas.apply {
                add(CategoryFoodTypeData(
                    categoryFoodTypeImg = value.categoryImg,
                    categoryFoodTypeTitle = value.categoryName) )
            }
        }

        // 레스토랑
        for ((index,value) in response.result.categoryStoreResult.withIndex()) {
            if(value.starRating == null && value.coupon == null ){
                categoryRestaurantDatas.apply {
                    add(
                        CategoryRestaurantData(
                            categoryMainImg = value.storeImg,
                            categoryUpImg = value.storeImg,
                            categoryDownImg = value.storeImg,
                            categoryName = value.name,
                            categoryReview = "",
                            categoryDistance = "",
                            categoryDeliveryFee = value.deliveryFee,
                            categoryDeliveryTime = value.deliveryTime,
                            categoryCheetahDelivery = value.fastDelivery,
                            categoryCoupon = ""
                        )
                    )
                }
            } else if(value.starRating == null){
                categoryRestaurantDatas.apply {
                    add(
                        CategoryRestaurantData(
                            categoryMainImg = value.storeImg,
                            categoryUpImg = value.storeImg,
                            categoryDownImg = value.storeImg,
                            categoryName = value.name,
                            categoryReview = "",
                            categoryDistance = "",
                            categoryDeliveryFee = value.deliveryFee,
                            categoryDeliveryTime = value.deliveryTime,
                            categoryCheetahDelivery = value.fastDelivery,
                            categoryCoupon = value.coupon
                        )
                    )
                }
            } else if(value.coupon == null) {
                categoryRestaurantDatas.apply {
                    add(
                        CategoryRestaurantData(
                            categoryMainImg = value.storeImg,
                            categoryUpImg = value.storeImg,
                            categoryDownImg = value.storeImg,
                            categoryName = value.name,
                            categoryReview = value.starRating + " (" + value.reviewCount.toString() + ")",
                            categoryDistance = "",
                            categoryDeliveryFee = value.deliveryFee,
                            categoryDeliveryTime = value.deliveryTime,
                            categoryCheetahDelivery = value.fastDelivery,
                            categoryCoupon = ""
                        )
                    )
                }
            } else {
                categoryRestaurantDatas.apply {
                    add(
                        CategoryRestaurantData(
                            categoryMainImg = value.storeImg,
                            categoryUpImg = value.storeImg,
                            categoryDownImg = value.storeImg,
                            categoryName = value.name,
                            categoryReview = value.starRating + " (" + value.reviewCount.toString() + ")",
                            categoryDistance = "",
                            categoryDeliveryFee = value.deliveryFee,
                            categoryDeliveryTime = value.deliveryTime,
                            categoryCheetahDelivery = value.fastDelivery,
                            categoryCoupon = value.coupon
                        )
                    )
                }
            }
        }

        initCategoryFoodTypeRecycler()
        initCategoryRestaurantRecycler()

    }

    override fun onGetCategoryFailure(message: String) {
        Log.d(TAG, "onGetCategoryFailure: $message")
    }

}