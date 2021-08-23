package com.yoonji.coupangeatsproject.src.log_in

import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.src.log_in.models.LogInResponse
import com.yoonji.coupangeatsproject.src.log_in.models.PostLogInRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInService(val view: LogInActivity)  {

    fun postLogIn(postLogInRequest: PostLogInRequest){
        val logdinRetrofitInterface = ApplicationClass.sRetrofit.create(LogInRetrofitInterface::class.java)

        logdinRetrofitInterface.postLogIn(postLogInRequest).enqueue(object : Callback<LogInResponse>{
            override fun onResponse(call: Call<LogInResponse>, response: Response<LogInResponse>) {
                view.onPostLogInSuccess(response.body() as LogInResponse)
            }

            override fun onFailure(call: Call<LogInResponse>, t: Throwable) {
                view.onPostLogInFailure(t.message ?: "통신 오류")
            }
        })
    }


}