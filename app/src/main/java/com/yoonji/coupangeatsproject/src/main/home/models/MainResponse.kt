package com.yoonji.coupangeatsproject.src.main.home.models

// MVP의 model에 해당
data class MainResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: NotLoginResult
)