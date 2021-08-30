package com.yoonji.coupangeatsproject.src.main.home.models

data class NotLoginResult(
    val bannerResult: List<BannerResult>,
    val categoryResult: List<CategoryResult>,
    val franchiseResult: List<FranchiseResult>,
    val storeListResult: List<StoreResult>
)