package com.yoonji.coupangeatsproject.src.main.home.models

data class MainLoginResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: LoginResult
)