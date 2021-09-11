package com.yoonji.coupangeatsproject.src.main.search

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.databinding.FragmentSearchBinding
import com.yoonji.coupangeatsproject.src.main.MainActivity
import com.yoonji.coupangeatsproject.src.main.home.HomeFragmentView

class SearchFragment: BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::bind, R.layout.fragment_search),
    SearchFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("TAG", "onViewCreated: search")

    }



}
