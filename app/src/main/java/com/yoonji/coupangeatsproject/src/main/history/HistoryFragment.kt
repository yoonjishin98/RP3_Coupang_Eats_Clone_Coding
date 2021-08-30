package com.yoonji.coupangeatsproject.src.main.history

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHistoryBinding

class HistoryFragment: BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::bind, R.layout.fragment_history),
    HistoryFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPagerHistory.adapter = HistoryAdapter(this@HistoryFragment)

        //탭과 뷰페이저를 연결, 여기서 새로운 탭을 다시 만드므로 레이아웃에서 꾸미지말고 여기서 꾸며야함
        TabLayoutMediator(binding.tabLayoutHistory, binding.viewPagerHistory) {tab, position ->
            when(position) {
                0 -> {
                    tab.text = "과거 주문 내역"

                }
                1 -> {
                    tab.text = "준비중"
                }
            }

        }.attach()

    }

}