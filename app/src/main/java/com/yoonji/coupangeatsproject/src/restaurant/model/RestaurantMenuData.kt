package com.yoonji.coupangeatsproject.src.restaurant.model

data class RestaurantMenuData(
    val restaurantMenuTitle:String,
    val menuDetailArrayList :MutableList<RestaurantDetailData>
)
