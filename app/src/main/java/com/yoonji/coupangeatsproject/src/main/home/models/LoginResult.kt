package com.yoonji.coupangeatsproject.src.main.home.models

data class LoginResult(
    val bannerResult: List<BannerResult>,
    val categoryResult: List<CategoryResult>,
    val inFranchiseResult: List<InFranchiseResult>,
    val inStoreListResult: List<InStoreResult>
)