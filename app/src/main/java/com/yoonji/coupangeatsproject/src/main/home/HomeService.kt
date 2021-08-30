package com.yoonji.coupangeatsproject.src.main.home

import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.src.main.home.models.MainLoginResponse
import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeService(val view: HomeFragmentView)  {

    fun getNotLoginMain(){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

        homeRetrofitInterface.getMainNotLogin().enqueue(object : Callback<MainResponse>{
            override fun onResponse(call: Call<MainResponse>, response: Response<MainResponse>) {
                view.onGetMainNotLoginSuccess(response.body() as MainResponse)
            }

            override fun onFailure(call: Call<MainResponse>, t: Throwable) {
                view.onGetMainNotLoginFailure(t.message ?: "통신오류")
            }
        })
    }

    fun getLoginMain(userIdx :Int) {
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)

        homeRetrofitInterface.getMainLogin(userIdx).enqueue(object : Callback<MainLoginResponse>{
            override fun onResponse(call: Call<MainLoginResponse>, response: Response<MainLoginResponse>) {
               view.onGetMainLoginSuccess(response.body() as MainLoginResponse)
            }

            override fun onFailure(call: Call<MainLoginResponse>, t: Throwable) {
                view.onGetMainNotLoginFailure(t.message ?: "통신오류")
            }


        })
    }

}