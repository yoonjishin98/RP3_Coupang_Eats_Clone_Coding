package com.yoonji.coupangeatsproject.src.restaurant.model

import androidx.recyclerview.widget.RecyclerView

data class RestaurantMenuData(
    val restaurantMenuTitle:String,
    val menuDetailArrayList :ArrayList<RestaurantDetailData>
)
