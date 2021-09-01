package com.yoonji.coupangeatsproject.src.main.home.models

data class storeMenuByCateResult(
    val Idx :String,
    val menuCategory: String,
    val menuCategoryDetail :List<menuCategoryDetailResult>
)
