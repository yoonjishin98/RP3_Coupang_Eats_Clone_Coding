package com.yoonji.coupangeatsproject.src.restaurant.model

data class RestaurantResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)