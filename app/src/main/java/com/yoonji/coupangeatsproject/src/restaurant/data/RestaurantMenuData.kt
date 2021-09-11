package com.yoonji.coupangeatsproject.src.restaurant.data

data class RestaurantMenuData(
    val restaurantMenuTitle:String,
    val menuDetailArrayList :MutableList<RestaurantDetailData>
)
