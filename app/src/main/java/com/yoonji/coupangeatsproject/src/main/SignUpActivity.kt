package com.yoonji.coupangeatsproject.src.main

import android.content.Intent
import android.os.Bundle
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnSignUp.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}