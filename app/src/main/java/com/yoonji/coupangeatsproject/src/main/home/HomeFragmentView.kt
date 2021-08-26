package com.yoonji.coupangeatsproject.src.main.home

import com.yoonji.coupangeatsproject.src.main.home.models.MainResponse

interface HomeFragmentView {
    fun onGetMainSuccess(response: MainResponse)
    fun onGetMainFailure(message: String)


}