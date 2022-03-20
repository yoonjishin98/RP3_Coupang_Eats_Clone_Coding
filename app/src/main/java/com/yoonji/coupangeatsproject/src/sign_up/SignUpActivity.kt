package com.yoonji.coupangeatsproject.src.sign_up

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
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
        var emailValidation = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        var checkValid1=0
        var checkValid2=0
        var checkValid3=0
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun checkEmail(){
            if(Pattern.matches(emailValidation, binding.edtSignupEmail.text.toString())){
                binding.vSignUpEmailBlue.visibility = GONE
                binding.vSignUpEmailRed.visibility = GONE
                binding.tvSignUpWarnEmail.visibility = GONE
                binding.imgvSignUpCheckEmail.visibility = VISIBLE
            } else if (binding.edtSignupEmail.text.toString() == "") {
                binding.vSignUpEmailBlue.visibility = GONE
                binding.vSignUpEmailRed.visibility = VISIBLE
                binding.imgvSignUpCheckEmail.visibility = GONE
                binding.tvSignUpWarnEmail.visibility = VISIBLE
                binding.tvSignUpWarnEmail.text = "이메일을 입력하세요"
            } else if (!Pattern.matches(emailValidation, binding.edtSignupEmail.text.toString())) {
                binding.vSignUpEmailBlue.visibility = GONE
                binding.vSignUpEmailRed.visibility = VISIBLE
                binding.imgvSignUpCheckEmail.visibility = GONE
                binding.tvSignUpWarnEmail.visibility = VISIBLE
                binding.tvSignUpWarnEmail.text = "이메일을 올바르게 입력해주세요"
            }else{
                binding.vSignUpEmailBlue.visibility = VISIBLE
            }
        }

        fun checkPwd(){
            if(checkValid1==1 && checkValid2==1 && checkValid3==1){
                binding.vSignUpPwdBlue.visibility = GONE
                binding.imgvSignUpCheckPwd.visibility = VISIBLE
            }
        }

        /* 이메일 유효성 검사 */
        binding.edtSignupEmail.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                if(binding.vSignUpEmailRed.visibility == VISIBLE)
                    binding.vSignUpEmailRed.visibility = VISIBLE
                else
                    binding.vSignUpEmailBlue.visibility = VISIBLE
            }else {
                checkEmail()
            }
        }

        binding.edtSignupEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(p0: CharSequence, start: Int, before: Int, count: Int) {
                if(!Pattern.matches(emailValidation, binding.edtSignupEmail.text.toString()))
                    binding.imgvSignUpCheckEmail.visibility = GONE
            }
            override fun afterTextChanged(p0: Editable) { }
        })

        /* 비밀번호 유효성 검사 */
        binding.edtSignupPwd.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                if(binding.vSignUpPwdRed.visibility == VISIBLE)
                    binding.vSignUpPwdRed.visibility = VISIBLE
                else if(binding.imgvSignUpCheckPwd.visibility == VISIBLE){
                    binding.vSignUpPwdBlue.visibility = VISIBLE
                    binding.xTwo.visibility = GONE
                    binding.xThree.visibility = GONE
                    binding.tvSignUpWarnPwdT.visibility = GONE
                    binding.tvSignUpWarnPwdTh.visibility = GONE
                }else {
                    binding.vSignUpPwdBlue.visibility = VISIBLE
                    binding.xOne.visibility = VISIBLE
                    binding.xTwo.visibility = VISIBLE
                    binding.xThree.visibility = VISIBLE
                    binding.tvSignUpWarnPwdO.visibility = VISIBLE
                    binding.tvSignUpWarnPwdT.visibility = VISIBLE
                    binding.tvSignUpWarnPwdTh.visibility = VISIBLE
                }
            }else
                checkPwd()
        }

        binding.edtSignupPwd.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) { }
            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                val firstCondition = "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{8,20}$"
                val secondCondition = "^(?!.*(.)\\1\\1.*).*\$"
                val red = ContextCompat.getColor(applicationContext, R.color.redline)
                val green = ContextCompat.getColor(applicationContext, R.color.greencheck)

                if(p0.isEmpty()) {     // 입력값이 없을 때
                    binding.vSignUpPwdRed.visibility = VISIBLE
                    binding.vSignUpPwdBlue.visibility = GONE
                    binding.xOne.setImageResource(R.drawable.ic_finish_sign__up)
                    binding.xTwo.setImageResource(R.drawable.ic_finish_sign__up)
                    binding.xThree.setImageResource(R.drawable.ic_finish_sign__up)
                    binding.xOne.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                    binding.xTwo.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                    binding.xThree.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                    binding.tvSignUpWarnPwdO.setTextColor(red)
                    binding.tvSignUpWarnPwdT.setTextColor(red)
                    binding.tvSignUpWarnPwdTh.setTextColor(red)
                }

                if(Pattern.matches(firstCondition, p0.toString())){     // 조건1. 영문/숫자/특수문자 2가지 이상 조합
                    binding.xOne.setImageResource(R.drawable.ic_check)
                    binding.xOne.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.greencheck))
                    binding.tvSignUpWarnPwdO.setTextColor(green)
                    checkValid1=1
                }else if(!Pattern.matches(firstCondition, p0.toString())){
                    binding.xOne.setImageResource(R.drawable.ic_finish_sign__up)
                    binding.xOne.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                    binding.tvSignUpWarnPwdO.setTextColor(red)
                    checkValid1=0
                }

                if(Pattern.compile(secondCondition).matcher(p0.toString()).find() && p0.toString().isNotEmpty()
                ){     // 조건2. 3개 이상 연속되거나 동일한 문자/숫자 제외
                    binding.xTwo.setImageResource(R.drawable.ic_check)
                    binding.xTwo.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.greencheck))
                    binding.tvSignUpWarnPwdT.setTextColor(green)
                    checkValid2=1
                }else if(!Pattern.compile(secondCondition).matcher(p0.toString()).find()){
                    binding.xTwo.setImageResource(R.drawable.ic_finish_sign__up)
                    binding.xTwo.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                    binding.tvSignUpWarnPwdT.setTextColor(red)
                    checkValid2=0
                }

                if(p0.toString()!=binding.edtSignupEmail.text.toString()){     // 조건3. 아이디(이메일) 제외
                    binding.xThree.setImageResource(R.drawable.ic_check)
                    binding.xThree.imageTintList = ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.greencheck))
                    binding.tvSignUpWarnPwdTh.setTextColor(green)
                    checkValid3=1
                }else if(p0.toString()==binding.edtSignupEmail.text.toString()) {
                    binding.xThree.setImageResource(R.drawable.ic_finish_sign__up)
                    binding.xThree.imageTintList =   ColorStateList.valueOf(this@SignUpActivity.getColor(R.color.redline))
                    binding.tvSignUpWarnPwdTh.setTextColor(red)
                    checkValid3=0
                }

                if(checkValid1==1 && checkValid2==1 && checkValid3==1){
                    binding.vSignUpPwdBlue.visibility = VISIBLE
                    binding.vSignUpPwdRed.visibility = GONE
                    binding.xOne.visibility = VISIBLE
                    binding.xTwo.visibility = GONE
                    binding.xThree.visibility = GONE
                    binding.tvSignUpWarnPwdO.text = "사용 가능한 비밀번호입니다"
                    binding.tvSignUpWarnPwdO.setTextColor(green)
                    binding.tvSignUpWarnPwdT.visibility = GONE
                    binding.tvSignUpWarnPwdTh.visibility = GONE
                    binding.imgvSignUpCheckPwd.visibility = VISIBLE
                }else{
                    binding.vSignUpPwdRed.visibility = VISIBLE
                    binding.vSignUpPwdBlue.visibility = GONE
                    binding.imgvSignUpCheckPwd.visibility = GONE
                    binding.tvSignUpWarnPwdO.text = "영문/숫자/특수문자 2가지 이상 조합(8~20자)"
                    binding.xTwo.visibility = VISIBLE
                    binding.tvSignUpWarnPwdT.visibility = VISIBLE
                    binding.xThree.visibility = VISIBLE
                    binding.tvSignUpWarnPwdTh.visibility = VISIBLE
                }
            }
            override fun afterTextChanged(p0: Editable) { }
        })

        /* 이름 유효성검사 */
        binding.edtSignupName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable) {
                if(binding.edtSignupName.text == null) {

                }
            }
            override fun beforeTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
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
        Log.d(TAG , "성공: " + response.message)

        if(response.isSuccess){
//            val editor: SharedPreferences.Editor = ApplicationClass.sSharedPreferences.edit()
//            editor.putString(ApplicationClass.X_ACCESS_TOKEN,response.result.jwt )
//            editor.apply()

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