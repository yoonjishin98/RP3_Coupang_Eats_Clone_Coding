package com.yoonji.coupangeatsproject.src.address_detail.models

data class AddressDetailResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)