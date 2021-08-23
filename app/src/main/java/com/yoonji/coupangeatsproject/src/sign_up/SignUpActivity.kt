package com.yoonji.coupangeatsproject.src.sign_up

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.View.*
import androidx.core.content.ContextCompat
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivitySignUpBinding
import com.yoonji.coupangeatsproject.src.main.MainActivity


class SignUpActivity : BaseActivity<ActivitySignUpBinding>(ActivitySignUpBinding::inflate) {

    companion object{
        var userEmail = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onResume() {
        super.onResume()

        binding.btnSignUp.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.imgvSignUpFinish.setOnClickListener{
            finish()
        }

        //이메일
        binding.tvSignupEmail.setOnTouchListener(object : View.OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        binding.imgvSignUpCheckEmail.visibility = GONE
                        binding.vSignUpEmailBlue.visibility = VISIBLE
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        binding.tvSignupEmail.addTextChangedListener(object : TextWatcher {
            //var emailValidation = Regex("[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$")
            var emailValidation = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}\$")
            var check = true

            override fun beforeTextChanged(p0: CharSequence, start: Int, count: Int, after: Int) {
                binding.tvSignUpWarnEmail.text = "이메일을 입력해주세요"


            }

            override fun onTextChanged(p0: CharSequence, start: Int, before: Int, count: Int) {
                //binding.tvSignUpWarnEmail.text = "이메일을 입력해주세요"

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
                }
                else if(!emailValidation.matches(p0.toString()) && p0.isNotEmpty()){
                    binding.vSignUpEmailRed.visibility = VISIBLE
                    binding.tvSignUpWarnEmail.text = "이메일을 올바르게 입력해주세요"
                    binding.imgvSignUpCheckEmail.visibility = GONE
                }
            }
        })

        //비밀번호
        binding.tvSignupPwd.setOnTouchListener(object : View.OnTouchListener {
            @SuppressLint("ClickableViewAccessibility")
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        binding.vSignUpPwdBlue.visibility = VISIBLE
                    }
                }

                return v?.onTouchEvent(event) ?: true
            }
        })

        binding.tvSignupPwd.addTextChangedListener(object : TextWatcher {
            var pwdReg = Regex("[a-zA-Z0-9!@#\$%^&*]{8,}")
            var check1 = false
            var check2 = false
            var check3 = false
            var check4 = false
            val red = ContextCompat.getColor(applicationContext, R.color.redline)
            val green = ContextCompat.getColor(applicationContext, R.color.greencheck)
            val blue = ContextCompat.getColor(applicationContext, R.color.blueline)

            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) { }

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                binding.vSignUpPwdRed.visibility = VISIBLE
                binding.xOne.setColorFilter(R.color.redline)
                binding.xTwo.setColorFilter(R.color.redline)
                binding.xThree.setColorFilter(R.color.redline)
                binding.tvSignUpWarnPwdO.setTextColor(red)
                binding.tvSignUpWarnPwdT.setTextColor(red)
                binding.tvSignUpWarnPwdTh.setTextColor(red)
                binding.xOne.visibility = VISIBLE
                binding.xTwo.visibility = VISIBLE
                binding.xThree.visibility = VISIBLE
                binding.tvSignUpWarnPwdO.visibility = VISIBLE
                binding.tvSignUpWarnPwdT.visibility = VISIBLE
                binding.tvSignUpWarnPwdTh.visibility = VISIBLE

                if(p0 != userEmail){    //세번째 조건
                    binding.xThree.setImageResource(R.drawable.ic_check)
                    binding.tvSignUpWarnPwdTh.setTextColor(green)
                    check1 = true
                }else{
                    check1 = false
                }

                if(pwdReg.matches(p0)){        //두번째 조건
                    binding.xTwo.setImageResource(R.drawable.ic_check)
                    binding.tvSignUpWarnPwdT.setTextColor(green)
                    check2 = true
                }else{
                    check2 = false
                }

                if(p3<8 || p3>20){
                    binding.xOne.setColorFilter(R.color.redline)
                    binding.tvSignUpWarnPwdO.setTextColor(red)
                    check3 = true
                }else{
                    check3 = false
                }

                if(p3>12){      //첫번째 조건: 임시설정
                    binding.xOne.setImageResource(R.drawable.ic_check)
                    binding.tvSignUpWarnPwdO.setTextColor(green)
                    check4 = true
                }else{
                    check4 = false
                }
            }

            override fun afterTextChanged(p0: Editable) {
                if(check1 && check2 && check3 && check4){
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

        binding.tvSignupName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable) {
                if(binding.tvSignupName.text == null) {

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

        binding.tvSignupPhoneNum.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable) {
                if(binding.tvSignupPhoneNum.text == null) {

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
}