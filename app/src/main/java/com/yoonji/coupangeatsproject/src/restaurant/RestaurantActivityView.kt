package com.yoonji.coupangeatsproject.src.restaurant

import com.yoonji.coupangeatsproject.src.category.models.CategoryResponse
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantResponse

interface RestaurantActivityView {
    fun onGetRestaurantSuccess(response: RestaurantResponse)
    fun onGetRestaurantFailure(message: String)

}