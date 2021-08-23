package com.yoonji.coupangeatsproject.src.log_in

import com.yoonji.coupangeatsproject.src.log_in.models.LogInResponse

interface LogInActivityView {
    fun onPostLogInSuccess(response: LogInResponse)
    fun onPostLogInFailure(message: String)
}