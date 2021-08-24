package com.yoonji.coupangeatsproject.src.address

import android.content.Intent
import android.os.Bundle
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityAddressBinding
import com.yoonji.coupangeatsproject.src.address_by_search.AddressBySearchActivity
import com.yoonji.coupangeatsproject.src.address_by_map.AddressByMapActivity

class AddressActivity  : BaseActivity<ActivityAddressBinding>(ActivityAddressBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        binding.imgvAddressFinish.setOnClickListener {
            finish()
        }

        binding.linearFindByCurrentLoca.setOnClickListener {
            val intent = Intent(this, AddressByMapActivity::class.java )
            startActivity(intent)
        }

        binding.tvAddressSerach.setOnClickListener{
            val  intent = Intent(this, AddressBySearchActivity::class.java )
            startActivity(intent)
        }

    }
}