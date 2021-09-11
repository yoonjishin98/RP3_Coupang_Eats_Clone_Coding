package com.yoonji.coupangeatsproject.src.sign_up

import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.src.sign_up.models.PostSignUpRequest
import com.yoonji.coupangeatsproject.src.sign_up.models.SignUpResponse
import retrofit2.Call
import retrofit2.Response

class SignUpService(val view: SignUpActivityView) {

    fun postSignUp(postSignUpRequest: PostSignUpRequest){
        val signUpRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpRetrofitInterface::class.java)

        signUpRetrofitInterface.postSignUp(postSignUpRequest).enqueue(object : retrofit2.Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                view.onPostSignUpSuccess(response.body() as SignUpResponse)
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }

        })

    }
}