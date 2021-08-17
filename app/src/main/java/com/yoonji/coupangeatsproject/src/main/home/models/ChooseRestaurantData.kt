package com.yoonji.coupangeatsproject.src.main.home.models

data class ChooseRestaurantData(
    val restaurantMainImg: Int,
    val restaurantUpImg: Int,
    val restaurantDownImg: Int,
    val restaurantName : String,
    val restaurantReview : String,
    val restaurantDistance :String,
    val restaurantDeliveryFee :String,
    val restaurantDeliveryTime :String
)
