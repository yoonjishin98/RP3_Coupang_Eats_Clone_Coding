package com.yoonji.coupangeatsproject.src.main

import android.content.Intent
import android.os.Bundle
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityLoginBinding

class LogInActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.imgvLoginFinish.setOnClickListener{
            finish()
        }

        binding.tvLoginSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}