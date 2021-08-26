package com.yoonji.coupangeatsproject.src.main.home.models

data class StoreResult(
    val Idx: Int,
    val coupon: String,
    val deliveryFee: String,
    val deliveryTime: String,
    val fastDelivery: String,
    val name: String,
    val reviewCount: Int,
    val starRating: String,
    val storeImg: String
)