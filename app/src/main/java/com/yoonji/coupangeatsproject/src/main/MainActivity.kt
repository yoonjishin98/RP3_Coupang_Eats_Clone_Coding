package com.yoonji.coupangeatsproject.src.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val TAG = "**MainActivity--->"
        val token = ApplicationClass.sSharedPreferences.getString(X_ACCESS_TOKEN, "")

        binding.btmNaviMain.run{
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.menu_bottom_home ->{
                        changeFragment(HomeFragment(),)
                    }
                    R.id.menu_bottom_search ->{
                        changeFragment(SearchFragment(),)
                    }
                    R.id.menu_bottom_like ->{
                        val intent = Intent(context, LikeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.menu_bottom_history ->{
                        Log.d(TAG, "token 값: $token")

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

                supportFragmentManager.beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                supportFragmentManager.beginTransaction().commit()
                supportFragmentManager.beginTransaction().isAddToBackStackAllowed
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


}