package com.yoonji.coupangeatsproject.src.restaurant

import com.yoonji.coupangeatsproject.src.category.models.CategoryResponse
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestaurantRetrofitInterface {
    @GET("/app/stores/{storeIdx}")
    fun getRestaurant(@Path("storeIdx") storeIdx:Int ) : Call<RestaurantResponse>
}