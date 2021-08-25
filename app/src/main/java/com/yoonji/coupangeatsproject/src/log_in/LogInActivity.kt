package com.yoonji.coupangeatsproject.src.log_in

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityLoginBinding
import com.yoonji.coupangeatsproject.src.log_in.models.LogInResponse
import com.yoonji.coupangeatsproject.src.log_in.models.PostLogInRequest
import com.yoonji.coupangeatsproject.src.main.MainActivity
import com.yoonji.coupangeatsproject.src.sign_up.SignUpActivity


class LogInActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate), LogInActivityView {

    private var TAG = "**LogInActivity--->"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.btnLoginLogin.setOnClickListener{
            val email = binding.edtLoginEmail.text.toString()
            val password = binding.edtLoginPwd.text.toString()
            val postRequest = PostLogInRequest(email = email, password = password)

            LogInService(this).postLogIn(postRequest)
        }

        binding.imgvLoginFinish.setOnClickListener{
            finish()
        }

        binding.tvLoginSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }




    override fun onPostLogInSuccess(response: LogInResponse) {
        response.message?.let { Log.d(TAG, "onPostLogInSuccess: $it , " +  response.result.jwt) }

        val editor: SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()       //sharedPreferences를 제어할 editor를 선언
        editor.putString(X_ACCESS_TOKEN,response.result.jwt )
        editor.apply()      //커밋을 해야 저장

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onPostLogInFailure(message: String) {
        Log.d(TAG, "onPostLogInFailure: $message")

        //이 부분은 원래 다이얼로그가 나와야 함
        showCustomToast("아이디 혹은 비밀번호가 일치하지 않습니다")
    }

}