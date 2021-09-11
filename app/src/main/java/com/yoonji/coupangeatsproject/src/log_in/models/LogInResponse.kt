package com.yoonji.coupangeatsproject.src.log_in.models

import com.google.gson.annotations.SerializedName
import com.yoonji.coupangeatsproject.BaseResponse

data class LogInResponse (
    // 베이스 리스폰스를 상속 받았으므로, 아래 내용은 포함
    // @SerializedName("isSuccess") val isSuccess: Boolean,
    // @SerializedName("code") val code: Int,
    // @SerializedName("message") val message: String,
    @SerializedName("result") val result: ResultLogIn
):BaseResponse()