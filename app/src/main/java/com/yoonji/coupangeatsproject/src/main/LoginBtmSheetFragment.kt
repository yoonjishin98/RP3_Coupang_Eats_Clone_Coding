package com.yoonji.coupangeatsproject.src.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.src.log_in.LogInActivity
import com.yoonji.coupangeatsproject.src.sign_up.SignUpActivity
import kotlinx.android.synthetic.main.fragment_login_btm_sheet.view.*


class LoginBtmSheetFragment() : BottomSheetDialogFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_login_btm_sheet,container,false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.tv_login_sign_up1.setOnClickListener {
            val intent = Intent(context, SignUpActivity::class.java)
            startActivity(intent)
        }
        view.tv_login_sign_up2.setOnClickListener{
            val intent = Intent(context, SignUpActivity::class.java)
            startActivity(intent)

        }

        view.btn_login_by_id.setOnClickListener{
            val intent = Intent(context, LogInActivity::class.java )
            startActivity(intent)
        }

    }

}