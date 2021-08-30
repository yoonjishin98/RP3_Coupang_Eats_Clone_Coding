package com.yoonji.coupangeatsproject.src.main.home

import com.yoonji.coupangeatsproject.src.main.home.models.MainLoginResponse
import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse

interface HomeFragmentView {
    fun onGetMainNotLoginSuccess(response: MainResponse)
    fun onGetMainNotLoginFailure(message: String)

    fun onGetMainLoginSuccess(response: MainLoginResponse)
    fun onGetMainLoginFailure(message: String)
}