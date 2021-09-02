package com.yoonji.coupangeatsproject.src.main.home.models

data class AddressResult(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: List<Result>
)