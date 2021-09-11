package com.yoonji.coupangeatsproject.src.sign_up

import android.telecom.Call
import com.yoonji.coupangeatsproject.src.sign_up.models.PostSignUpRequest
import com.yoonji.coupangeatsproject.src.sign_up.models.SignUpResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRetrofitInterface {
    @POST("/app/users")
    fun postSignUp(@Body params: PostSignUpRequest) : retrofit2.Call<SignUpResponse>
}