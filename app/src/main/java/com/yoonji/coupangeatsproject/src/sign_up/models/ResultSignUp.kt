package com.yoonji.coupangeatsproject.src.sign_up.models

import com.google.gson.annotations.SerializedName

data class ResultSignUp(
    @SerializedName("userId") val userId:Int,
    @SerializedName("jwt") val jwt: String
)
