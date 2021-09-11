package com.yoonji.coupangeatsproject.src.address_detail

import com.yoonji.coupangeatsproject.src.address_detail.models.AddressDetailRequest
import com.yoonji.coupangeatsproject.src.address_detail.models.AddressDetailResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AddressDetailRetrofitInterface {
    @POST("/app/users/newaddress")
    fun postAddressDetail(@Body params: AddressDetailRequest) : retrofit2.Call<AddressDetailResponse>
}