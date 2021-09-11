package com.yoonji.coupangeatsproject.src.log_in

import com.yoonji.coupangeatsproject.src.log_in.models.LogInResponse
import com.yoonji.coupangeatsproject.src.log_in.models.PostLogInRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface LogInRetrofitInterface {
    @POST("/app/login")
    fun postLogIn(@Body params: PostLogInRequest) : Call<LogInResponse>
}