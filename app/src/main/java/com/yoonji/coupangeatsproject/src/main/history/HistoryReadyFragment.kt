package com.yoonji.coupangeatsproject.src.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHistoryBinding
import com.yoonji.coupangeatsproject.databinding.FragmentHistoryReadyBinding

class HistoryReadyFragment: BaseFragment<FragmentHistoryReadyBinding>(FragmentHistoryReadyBinding::bind, R.layout.fragment_history_ready) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

        binding.btnHistoryReady.setOnClickListener {

        }

    }
}