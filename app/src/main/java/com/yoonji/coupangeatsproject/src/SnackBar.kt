//package com.yoonji.coupangeatsproject.src
//
//import android.view.LayoutInflater
//import android.view.View
//import androidx.core.content.ContextCompat
//import com.google.android.material.snackbar.Snackbar
//import com.yoonji.coupangeatsproject.R
//import com.yoonji.coupangeatsproject.databinding.SnackbarChangeAddressBinding
//
//class SnackBar(view: View, private val msg:String) {
//    companion object{
//        fun make(view: View, message: String) = SnackBar(view, message)
//    }
//
//    private val context = view.context
//    private val snackbar = Snackbar.make(view, "", 3000)
//    private val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
//
//    private val inflater = LayoutInflater.from(context)
//    private val snackbarBinding: SnackbarChangeAddressBinding = DataBindingUtil.inflate(inflater, R.layout.snackbar_change_address, null, false)
//
//    init {
//        initView()
//        initData()
//    }
//
//    private fun initView() {
//        with(snackbarLayout) {
//            removeAllViews()
//            setPadding(0, 0, 0, 0)
//            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
//            addView(snackbarBinding.root, 0)
//        }
//    }
//
//    private fun initData() {
//
//    }
//
//    fun show() {
//        snackbar.show()
//    }
//
//}