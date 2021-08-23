package com.yoonji.coupangeatsproject.src.log_in.models

import com.google.gson.annotations.SerializedName

data class ResultLogIn (
    @SerializedName("userIdx") val userIdx: Int,
    @SerializedName("jwt") val jwt: String
    )