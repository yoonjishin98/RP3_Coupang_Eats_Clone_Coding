package com.yoonji.coupangeatsproject.src.sign_up.models

import com.google.gson.annotations.SerializedName

data class PostSignUpRequest (
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("name") val name: String,
    @SerializedName("phonenum") val phonenum: String,
)