package com.yoonji.coupangeatsproject.src.restaurant.model

data class Menu(
    val menuCategory: Int,
    val menuIdx: Int,
    val menuImg: String,
    val menuInfo: String,
    val name: String,
    val price: String
)