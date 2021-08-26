package com.yoonji.coupangeatsproject.src

import android.content.Intent
import android.os.Bundle
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddressBySearchBinding
import com.yoonji.coupangeatsproject.databinding.ActivityAddressDetailBinding
import com.yoonji.coupangeatsproject.src.main.MainActivity

class AddressDetailActivity : BaseActivity<ActivityAddressDetailBinding>(ActivityAddressDetailBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnAddressDetailFinish.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            intent.putExtra("changeAddress",111)
            startActivity(intent)
        }
    }

}