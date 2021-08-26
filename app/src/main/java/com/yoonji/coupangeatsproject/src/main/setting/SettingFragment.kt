package com.yoonji.coupangeatsproject.src.main.setting

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.databinding.FragmentSettingBinding
import com.yoonji.coupangeatsproject.src.main.home.HomeFragmentView

class SettingFragment: BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting),
    SettingFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.end.setOnClickListener{
            val editor: SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()       //sharedPreferences를 제어할 editor를 선언
            editor.putString(ApplicationClass.X_ACCESS_TOKEN, null )
            editor.apply()      //커밋을 해야 저장

            Log.d("TAG", "onViewCreated: " +
                    ApplicationClass.sSharedPreferences.getString(ApplicationClass.X_ACCESS_TOKEN, "") )
        }

    }
}