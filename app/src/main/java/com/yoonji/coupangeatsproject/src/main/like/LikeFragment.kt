package com.yoonji.coupangeatsproject.src.main.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding
import com.yoonji.coupangeatsproject.databinding.FragmentLikeBinding
import com.yoonji.coupangeatsproject.src.main.home.HomeFragmentView

class LikeFragment : BaseFragment<FragmentLikeBinding>(FragmentLikeBinding::bind, R.layout.fragment_like),
    LikeFragmentView {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}