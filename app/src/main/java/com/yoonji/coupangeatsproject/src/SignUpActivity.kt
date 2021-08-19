package com.yoonji.coupangeatsproject.src

import android.content.Intent
import android.os.Bundle
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivitySignUpBinding
import com.yoonji.coupangeatsproject.src.main.MainActivity

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

        binding.btnSignUp.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imgvSignUpFinish.setOnClickListener{
            finish()
        }
    }
}