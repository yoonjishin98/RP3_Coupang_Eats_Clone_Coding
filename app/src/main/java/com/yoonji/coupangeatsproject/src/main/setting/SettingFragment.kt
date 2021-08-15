package com.yoonji.coupangeatsproject.src.main.setting

import android.os.Bundle
import android.view.View
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.databinding.FragmentSettingBinding
import com.yoonji.coupangeatsproject.src.main.home.HomeFragmentView

class SettingFragment: BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting),
    SettingFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }
}