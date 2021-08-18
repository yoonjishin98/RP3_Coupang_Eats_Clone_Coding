package com.yoonji.coupangeatsproject.src.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yoonji.coupangeatsproject.R
import kotlinx.android.synthetic.main.fragment_login_btm_sheet.view.*


class LoginBtmSheetFragment(val itemClick: (Int) -> Unit) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_login_btm_sheet,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.tv_login_sign_up1.setOnClickListener {
            itemClick(0)
        }
        view.tv_login_sign_up2.setOnClickListener{
            itemClick(1)
            //dialog?.dismiss()
        }

        view.btn_login_by_id.setOnClickListener{
            itemClick(2)
        }

    }

}