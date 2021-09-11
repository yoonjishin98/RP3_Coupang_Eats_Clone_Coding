package com.yoonji.coupangeatsproject.src.main.home.models

data class FranchiseResult(
    val Idx: Int,
    val coupon_or_deliveryFee: String,
    val name: String,
    val reviewCount: Int,
    val starRating: String,
    val storeImg: String
)