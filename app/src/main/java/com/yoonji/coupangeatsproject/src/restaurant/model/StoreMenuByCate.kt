package com.yoonji.coupangeatsproject.src.restaurant.model

data class StoreMenuByCate(
    val Idx: Int,
    val menu: List<Menu>,
    val menuCategory: String
)