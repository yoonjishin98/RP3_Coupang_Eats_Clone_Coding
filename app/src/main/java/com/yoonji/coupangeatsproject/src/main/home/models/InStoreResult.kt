package com.yoonji.coupangeatsproject.src.main.home.models

data class InStoreResult(
    val Idx: Int,
    val coupon: String,
    val deliveryFee: String,
    val deliveryTime: String,
    val dist: String,
    val fastDelivery: String,
    val name: String,
    val reviewCount: Int,
    val starRating: String,
    val storeImg: String
)