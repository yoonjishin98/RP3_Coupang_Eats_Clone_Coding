package com.yoonji.coupangeatsproject.src.restaurant.model

data class StoreInfoResult(
    val Idx: Int,
    val coupon: String,
    val deliveryFee: String,
    val deliveryTime: String,
    val fastDelivery: String,
    val minOrderPrice: String,
    val name: String,
    val reviewCount: Int,
    val starRating: String
)