package com.yoonji.coupangeatsproject.src.sign_up.models

import com.google.gson.annotations.SerializedName
import com.yoonji.coupangeatsproject.BaseResponse

data class SignUpResponse (
    @SerializedName("result") val result:ResultSignUp
    ):BaseResponse()