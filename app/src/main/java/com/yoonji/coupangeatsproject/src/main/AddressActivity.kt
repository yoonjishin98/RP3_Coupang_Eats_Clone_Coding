package com.yoonji.coupangeatsproject.src.main

import android.os.Bundle
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddressBinding

class AddressActivity  : BaseActivity<ActivityAddressBinding>(ActivityAddressBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        binding.imgvAddressFinish.setOnClickListener {
            finish()
        }
    }
}