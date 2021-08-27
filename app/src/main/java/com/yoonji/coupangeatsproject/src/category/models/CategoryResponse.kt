package com.yoonji.coupangeatsproject.src.category.models

data class CategoryResponse(
    val code: Int,
    val isSuccess: Boolean,
    val message: String,
    val result: Result
)