package com.yoonji.coupangeatsproject.src.address_detail

import com.yoonji.coupangeatsproject.src.address_detail.models.AddressDetailResponse
import com.yoonji.coupangeatsproject.src.category.models.CategoryResponse

interface AddressDetailActivityView {
    fun onPostAddressDetailSuccess(response: AddressDetailResponse)
    fun onPostAddressDetailFailure(message: String)
}