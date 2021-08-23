package com.yoonji.coupangeatsproject.src.log_in.models

import com.google.gson.annotations.SerializedName

data class PostLogInRequest (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)