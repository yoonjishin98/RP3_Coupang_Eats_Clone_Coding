package com.yoonji.coupangeatsproject.src.address_detail.models

data class AddressDetailRequest (
    val userIdx:Int,
    val address:String,
    val latitude:Double,
    val longtitude:Double
    )