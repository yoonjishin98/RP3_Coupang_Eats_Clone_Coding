package com.yoonji.coupangeatsproject.src.main.like

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityLikeBinding
import com.yoonji.coupangeatsproject.databinding.ActivityMainBinding
import com.yoonji.coupangeatsproject.src.main.MainActivity

class LikeActivity : BaseActivity<ActivityLikeBinding>(ActivityLikeBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnLikeGoHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imgvLikeBefore.setOnClickListener{
           finish()
        }

    }
}