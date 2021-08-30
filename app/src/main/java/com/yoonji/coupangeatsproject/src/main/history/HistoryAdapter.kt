package com.yoonji.coupangeatsproject.src.main.history

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HistoryAdapter(fragment: HistoryFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HistoryListFragment()
            else -> HistoryReadyFragment()

        }
    }


}