package com.yoonji.coupangeatsproject.src.main.home

import com.yoonji.coupangeatsproject.src.main.home.models.MainLoginResponse
import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeRetrofitInterface {
    @GET("/app/main")
    fun getMainNotLogin() :Call<MainResponse>

    @GET ("/app/users/{userIdx}/main")
    fun getMainLogin(@Path("userIdx") userIdx:Int) :Call<MainLoginResponse>
}