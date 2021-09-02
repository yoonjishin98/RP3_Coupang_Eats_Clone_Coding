package com.yoonji.coupangeatsproject.src.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityMainBinding
import com.yoonji.coupangeatsproject.src.main.history.HistoryFragment
import com.yoonji.coupangeatsproject.src.main.home.HomeFragment
import com.yoonji.coupangeatsproject.src.main.like.LikeActivity
import com.yoonji.coupangeatsproject.src.main.search.SearchFragment
import com.yoonji.coupangeatsproject.src.main.setting.SettingFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    val TAG = "**MainActivity--->"
    val token = ApplicationClass.sSharedPreferences.getString(X_ACCESS_TOKEN, "")
    val userIdxFromSharedPreferences = ApplicationClass.sSharedPreferences.getInt("userIdx", -1)
    var newAddress = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btmNaviMain.run{
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.menu_bottom_home ->{
                        val userIdx = intent.getIntExtra("userIdx",userIdxFromSharedPreferences)

                        if(token == ""){
                            changeFragment(HomeFragment())
                        }else{
                            val homeF = HomeFragment()
                            val bundle = Bundle()

                            bundle.putInt("userIdx", userIdx)
                            homeF.arguments = bundle

                            changeFragment(homeF)
//                            val transaction = supportFragmentManager.beginTransaction()
//                            transaction.add(R.id.main_frm, homeF)
//                            transaction.commit()
                        }
                    }
                    R.id.menu_bottom_search ->{
                        changeFragment( SearchFragment() )
                    }
                    R.id.menu_bottom_like ->{
                        val intent = Intent(context, LikeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.menu_bottom_history ->{
                        Log.d(TAG, "token 값: $token")

                        if(token == "" ){
                            val bottomSheet = LoginBtmSheetFragment()
                            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                        }else
                            changeFragment(HistoryFragment())
                    }
                    R.id.menu_bottom_setting ->{
                        if(token == ""){
                            val bottomSheet = LoginBtmSheetFragment()
                            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                        }else
                            changeFragment(SettingFragment())
                    }
                }

                //supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                supportFragmentManager.beginTransaction().commit()
                true
            }
            selectedItemId = R.id.menu_bottom_home
        }


    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frm, fragment)
            .commit()
    }

    override fun onResume() {
        super.onResume()

        val check = intent.getIntExtra("changeAddress",0)
        val orderDone = intent.getIntExtra("orderDone",0)

        if(check == 111) {
            newAddress = intent.getStringExtra("newAddress").toString()
            Log.d(TAG, "건너온 newAddress 값: $newAddress")

            showCustomToast("배달 주소가 변경되었습니다.")
        }

        if(orderDone == 222){
            showCustomToast("주문이 완료되었습니다.")
        }
    }

    override fun onRestart() {
        super.onRestart()

        binding.btmNaviMain.run{
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.menu_bottom_home ->{
                        val userIdx = intent.getIntExtra("userIdx",userIdxFromSharedPreferences)

                        if(token == ""){
                            changeFragment(HomeFragment())
                        }else{
                            val homeF = HomeFragment()
                            val bundle = Bundle()

                            bundle.putInt("userIdx", userIdx)
                            homeF.arguments = bundle

                            changeFragment(homeF)
                        }
                    }
                    R.id.menu_bottom_search ->{
                        changeFragment(SearchFragment())
                    }
                    R.id.menu_bottom_like ->{
                        val intent = Intent(context, LikeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.menu_bottom_history ->{

                        if(token == ""){
                            val bottomSheet = LoginBtmSheetFragment()
                            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                        }else
                            changeFragment(HistoryFragment())
                    }
                    R.id.menu_bottom_setting ->{

                        if(token == ""){
                            val bottomSheet = LoginBtmSheetFragment()
                            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                        }else
                            changeFragment(SettingFragment())
                    }
                }

//                supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                supportFragmentManager.beginTransaction().commit()
                true
            }
        }

    }


}