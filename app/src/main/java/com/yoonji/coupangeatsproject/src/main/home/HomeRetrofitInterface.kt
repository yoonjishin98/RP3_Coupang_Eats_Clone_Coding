package com.yoonji.coupangeatsproject.src.main.home

import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse
import retrofit2.Call
import retrofit2.http.GET

interface HomeRetrofitInterface {
    @GET("/app/main")
    fun getMain() :Call<MainResponse>

}