package com.yoonji.coupangeatsproject.src.main.home.models

data class MainResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: NotLoginResult
)