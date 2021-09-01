package com.yoonji.coupangeatsproject.src.restaurant.model

data class Result(
    val storeImgResult: List<StoreImgResult>,
    val storeInfoResult: List<StoreInfoResult>,
    val storeMenuByCate: List<Any>,
    val storeMenuCateResult: List<StoreMenuCateResult>,
    val storePtReviewResult: List<StorePtReviewResult>
)