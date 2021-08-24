package com.yoonji.coupangeatsproject.src.address_by_map.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface KakaoApiService {
    @GET("/v2/local/search/keyword.json")

    fun getKakaoAddress(
        @Header("Authorization") key:String,
        @Query("query") address:String
    ) : Call<RoadAddress>
}