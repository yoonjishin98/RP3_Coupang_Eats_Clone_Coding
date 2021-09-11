package com.yoonji.coupangeatsproject

import com.yoonji.coupangeatsproject.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.yoonji.coupangeatsproject.ApplicationClass.Companion.sSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sSharedPreferences.getString(X_ACCESS_TOKEN, null)
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken)       //서버에 요청보낼 때 http 헤더에 붙여서 클라를 식별할 수 있도록 함
        }
        return chain.proceed(builder.build())
    }
}