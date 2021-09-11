package com.yoonji.coupangeatsproject.src.address_detail

import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.src.address_detail.models.AddressDetailRequest
import com.yoonji.coupangeatsproject.src.address_detail.models.AddressDetailResponse
import com.yoonji.coupangeatsproject.src.sign_up.SignUpActivityView
import com.yoonji.coupangeatsproject.src.sign_up.SignUpRetrofitInterface
import com.yoonji.coupangeatsproject.src.sign_up.models.PostSignUpRequest
import com.yoonji.coupangeatsproject.src.sign_up.models.SignUpResponse
import retrofit2.Call
import retrofit2.Response

class AddressDetailService(val view: AddressDetailActivityView)  {

    fun postAddressDetail(postAddressDetailRequest: AddressDetailRequest){
        val addressDetailRetrofitInterface = ApplicationClass.sRetrofit.create(AddressDetailRetrofitInterface::class.java)

        addressDetailRetrofitInterface.postAddressDetail(postAddressDetailRequest).enqueue(object : retrofit2.Callback<AddressDetailResponse> {
            override fun onResponse(
                call: Call<AddressDetailResponse>,
                response: Response<AddressDetailResponse>
            ) {
                view.onPostAddressDetailSuccess(response.body() as AddressDetailResponse)
            }

            override fun onFailure(call: Call<AddressDetailResponse>, t: Throwable) {
                view.onPostAddressDetailFailure(t.message ?: "통신 오류")
            }

        })

    }
}