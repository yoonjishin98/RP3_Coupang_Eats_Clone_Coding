package com.yoonji.coupangeatsproject.src.sign_up

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View.*
import androidx.core.content.ContextCompat
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivitySignUpBinding
import com.yoonji.coupangeatsproject.src.main.MainActivity
import com.yoonji.coupangeatsproject.src.sign_up.models.PostSignUpRequest
import com.yoonji.coupangeatsproject.src.sign_up.models.SignUpResponse
import java.util.regex.Pattern


class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate),SignUpActivityView {

    private var TAG = "**SignUpActivity--->"

    companion object{
        var userEmail = ""
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //이메일
        binding.edtSignupEmail.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.imgvSignUpCheckEmail.visibility = GONE
                    binding.vSignUpEmailBlue.visibility = VISIBLE
                }
            }

            v?.onTouchEvent(event) ?: true
        }

        binding.edtSignupEmail.addTextChangedListener(object : TextWatcher {
            var emailValidation = Regex("[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")
            var check = true

            override fun beforeTextChanged(p0: CharSequence, start: Int, count: Int, after: Int) {
                binding.tvSignUpWarnEmail.text = "이메일을 입력해주세요"
            }

            override fun onTextChanged(p0: CharSequence, start: Int, before: Int, count: Int) {

                if(check) {
                    binding.vSignUpEmailBlue.visibility = VISIBLE
                    binding.imgvSignUpCheckEmail.visibility = GONE
                }
                if(count == 0 ) {
                    binding.vSignUpEmailBlue.visibility = GONE
                    binding.vSignUpEmailRed.visibility = VISIBLE
                    binding.tvSignUpWarnEmail.visibility = VISIBLE
                    binding.imgvSignUpCheckEmail.visibility = GONE

                    check = false
                }
            }

            override fun afterTextChanged(p0: Editable) {
                if(emailValidation.matches(p0.toString()) ){
                    binding.vSignUpEmailBlue.visibility = GONE
                    binding.vSignUpEmailRed.visibility = GONE
                    binding.tvSignUpWarnEmail.visibility = GONE
                    binding.imgvSignUpCheckEmail.visibility = VISIBLE

                    userEmail = p0.toString()

                } else if(!emailValidation.matches(p0.toString()) && p0.isNotEmpty()){
                    binding.vSignUpEmailRed.visibility = VISIBLE
                    binding.tvSignUpWarnEmail.text = "이메일을 올바르게 입력해주세요"
                    binding.imgvSignUpCheckEmail.visibility = GONE

                } else if(p0.toString()==""){
                    binding.vSignUpEmailRed.visibility = VISIBLE
                    binding.tvSignUpWarnEmail.visibility = VISIBLE
                }
            }
        })

        //비밀번호
        binding.edtSignupPwd.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.vSignUpPwdBlue.visibility = VISIBLE
                    binding.xOne.visibility = VISIBLE
                    binding.xTwo.visibility = VISIBLE
                    binding.xThree.visibility = VISIBLE
                    binding.tvSignUpWarnPwdO.visibility = VISIBLE
                    binding.tvSignUpWarnPwdT.visibility = VISIBLE
                    binding.tvSignUpWarnPwdTh.visibility = VISIBLE
                }
            }
            v?.onTouchEvent(event) ?: true
        }

        binding.edtSignupPwd.addTextChangedListener(object : TextWatcher {
            var firstCondition = Regex("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,20}\$\n")
            var secondCondition = "(\\\\w)\\\\1\\\\1"
            var check1 = false
            var check2 = false
            var check3 = false
            val red = ContextCompat.getColor(applicationContext, R.color.redline)
            val green = ContextCompat.getColor(applicationContext, R.color.greencheck)
            val blue = ContextCompat.getColor(applicationContext, R.color.blueline)

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                if(p0.toString()=="") {
                    binding.vSignUpPwdRed.visibility = INVISIBLE
                    binding.xOne.visibility = VISIBLE
                    binding.xTwo.visibility = VISIBLE
                    binding.xThree.visibility = VISIBLE
                    binding.tvSignUpWarnPwdO.visibility = VISIBLE
                    binding.tvSignUpWarnPwdT.visibility = VISIBLE
                    binding.tvSignUpWarnPwdTh.visibility = VISIBLE
                }

                binding.vSignUpPwdRed.visibility = VISIBLE
                binding.xOne.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                binding.xTwo.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                binding.xThree.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                binding.tvSignUpWarnPwdO.setTextColor(red)
                binding.tvSignUpWarnPwdT.setTextColor(red)
                binding.tvSignUpWarnPwdTh.setTextColor(red)
                binding.xOne.visibility = VISIBLE
                binding.xTwo.visibility = VISIBLE
                binding.xThree.visibility = VISIBLE
                binding.tvSignUpWarnPwdO.visibility = VISIBLE
                binding.tvSignUpWarnPwdT.visibility = VISIBLE
                binding.tvSignUpWarnPwdTh.visibility = VISIBLE

                check3 = if(p0 != userEmail){    //세 번째 조건
                    binding.xThree.setImageResource(R.drawable.ic_check)
                    binding.tvSignUpWarnPwdTh.setTextColor(green)
                    true
                }else{
                    false
                }

                check1 = if(!firstCondition.matches(p0)){        //첫 번째 조건
                    binding.xOne.setImageResource(R.drawable.ic_check)
                    binding.tvSignUpWarnPwdO.setTextColor(green)
                    true
                }else{
                    false
                }

                if(!Pattern.compile(secondCondition).matcher(p0).find()){      // 두 번째 조건
                    binding.xTwo.setImageResource(R.drawable.ic_check)
                    binding.tvSignUpWarnPwdT.setTextColor(green)
                    check2 = true
                }else{
                    check2 = false
                }

            }

            override fun afterTextChanged(p0: Editable) {
                if(check1 && check2 && check3){
                    binding.xOne.visibility = VISIBLE
                    binding.xTwo.visibility = GONE
                    binding.xThree.visibility = GONE
                    binding.tvSignUpWarnPwdO.text = "사용 가능한 비밀번호입니다"
                    binding.tvSignUpWarnPwdO.setTextColor(blue)
                    binding.tvSignUpWarnPwdO.visibility = VISIBLE
                    binding.tvSignUpWarnPwdT.visibility = GONE
                    binding.tvSignUpWarnPwdTh.visibility = GONE
                }


            }
        })

        binding.edtSignupName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable) {
                if(binding.edtSignupName.text == null) {

                }
            }
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
//                binding.vSignUpEmailBlue.visibility = VISIBLE

//                binding.tvSignUpWarnPwdO.visibility = VISIBLE
//                binding.tvSignUpWarnPwdT.visibility = VISIBLE
//                binding.tvSignUpWarnPwdTh.visibility = VISIBLE
            }
        })

        binding.edtSignupPhoneNum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable) {
                if(binding.edtSignupPhoneNum.text == null) {

                }
            }
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
//                binding.vSignUpEmailBlue.visibility = VISIBLE
//
//                binding.tvSignUpWarnPwdO.visibility = VISIBLE
//                binding.tvSignUpWarnPwdT.visibility = VISIBLE
//                binding.tvSignUpWarnPwdTh.visibility = VISIBLE
            }
        })


    }

    override fun onResume() {
        super.onResume()
        var check = 0
        var check2 = 0

        binding.btnSignUp.setOnClickListener{
            val email = binding.edtSignupEmail.text.toString()
            val pwd = binding.edtSignupPwd.text.toString()
            val name = binding.edtSignupName.text.toString()
            val phoneNum = binding.edtSignupPhoneNum.text.toString()

            val postRequest = PostSignUpRequest(email = email, password = pwd, name = name, phonenum = phoneNum)
            SignUpService(this).postSignUp(postRequest)
        }

        binding.imgvSignUpFinish.setOnClickListener{
            finish()
        }


        binding.checkSignUpAll.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                binding.checkBox2.isChecked = true
                binding.checkBox3.isChecked = true
                binding.checkBox4.isChecked = true
                binding.checkBox5.isChecked = true
                binding.checkBox6.isChecked = true
                check = 0
            }else if(!isChecked && check==0){
                binding.checkBox2.isChecked = false
                binding.checkBox3.isChecked = false
                binding.checkBox4.isChecked = false
                binding.checkBox5.isChecked = false
                binding.checkBox6.isChecked = false
            }
        }

        binding.checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!isChecked) {
                check = 100
                check2 = 0
                binding.checkSignUpAll.isChecked = false
            }else{
                check2 = -100
            }

        }
        binding.checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!isChecked){
                check = 100
                check2 = 0
                binding.checkSignUpAll.isChecked = false
            }else{
                check2 = -100
            }

        }
        binding.checkBox4.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!isChecked){
                check = 100
                check2 = 0
                binding.checkSignUpAll.isChecked = false
            }else{
                check2 = -100
            }

        }
        binding.checkBox5.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!isChecked){
                check = 100
                check2 = 0
                binding.checkSignUpAll.isChecked = false
            }else{
                check2 = -100
            }

        }
        binding.checkBox6.setOnCheckedChangeListener { buttonView, isChecked ->
            if(!isChecked){
                check = 100
                check2 = 0
                binding.checkSignUpAll.isChecked = false
            }else{
                check2 = -100
            }
        }


    }

    override fun onPostSignUpSuccess(response: SignUpResponse) {
        Log.d(TAG , "성공: " + response.result)

        if(response.isSuccess){
            val editor: SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
            editor.putString(ApplicationClass.X_ACCESS_TOKEN,response.result.jwt )
            editor.apply()      //커밋을 해야 저장

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else if(response.code == 2001){

        }else if(response.code == 2002){

        }else if(response.code == 2003){

        }else if(response.code == 2004){

        }else if(response.code == 2005){

        }else if(response.code == 2019){

        }else if(response.code == 2020){

        }else if(response.code == 2021){

        }

    }

    override fun onPostSignUpFailure(message: String) {
        Log.d(TAG, "오류 : $message")
    }

}