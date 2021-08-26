package com.yoonji.coupangeatsproject.src.main.home

import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView)  {
    fun getNotLoginMain(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getMain().enqueue(object : Callback<MainResponse>{
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                view.onGetMainSuccess(response.body() as MainResponse)
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                view.onGetMainFailure(t.message ?: "통신오류")
            }

        })
    }

    fun getLoginMain() {
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

    }

}