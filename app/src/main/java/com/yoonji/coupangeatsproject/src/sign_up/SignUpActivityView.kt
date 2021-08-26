package com.yoonji.coupangeatsproject.src.sign_up

import com.yoonji.coupangeatsproject.src.sign_up.models.SignUpResponse

interface SignUpActivityView {

    fun onPostSignUpSuccess(response: SignUpResponse)
    fun onPostSignUpFailure(message: String)
}