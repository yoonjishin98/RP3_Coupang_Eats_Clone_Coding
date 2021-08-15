package com.yoonji.coupangeatsproject.src.main.history

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.yoonji.coupangeatsproject.BaseFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.FragmentHistoryBinding
import com.yoonji.coupangeatsproject.databinding.FragmentHomeBinding

class HistoryFragment: BaseFragment<FragmentHistoryBinding>(FragmentHistoryBinding::bind, R.layout.fragment_history),
    HistoryFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

}