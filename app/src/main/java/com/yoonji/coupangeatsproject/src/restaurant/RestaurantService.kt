package com.yoonji.coupangeatsproject.src.restaurant

import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.src.category.CategoryRetrofitInterface
import com.yoonji.coupangeatsproject.src.category.models.CategoryResponse
import com.yoonji.coupangeatsproject.src.restaurant.model.RestaurantResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RestaurantService(val view: RestaurantActivityView) {

    fun getRestaurantDetail(storeIdx :Int){
        val restaurantRetrofitInterface = ApplicationClass.sRetrofit.create(RestaurantRetrofitInterface::class.java)

        restaurantRetrofitInterface.getRestaurant(storeIdx).enqueue(object :
            Callback<RestaurantResponse> {
            override fun onResponse(
                call: Call<RestaurantResponse>,
                response: Response<RestaurantResponse>) {
                view.onGetRestaurantSuccess(response.body() as RestaurantResponse)
            }

            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
                view.onGetRestaurantFailure(t.message ?: "통신오류")
            }

        })
    }
}