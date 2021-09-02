package com.yoonji.coupangeatsproject.src.restaurant.model

data class Result(
    val storeImgResult: List<StoreImgResult>,
    val storeInfoResult: List<StoreInfoResult>,
    val storeMenuByCate: List<StoreMenuByCate>,
    val storePtReviewResult: List<StorePtReviewResult>
)