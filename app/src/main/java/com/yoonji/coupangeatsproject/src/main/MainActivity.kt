package com.yoonji.coupangeatsproject.src.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yoonji.coupangeatsproject.ApplicationClass
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.config.BaseActivity
import com.yoonji.coupangeatsproject.databinding.ActivityMainBinding
import com.yoonji.coupangeatsproject.src.main.home.HomeFragment
import com.yoonji.coupangeatsproject.src.main.like.LikeActivity
import com.yoonji.coupangeatsproject.src.main.search.SearchFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val TAG = "**MainActivity--->"

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
                        //changeFragment(HistoryFragment(),"history")
//                        val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")
//                        Log.d(TAG, "onCreate: $jwt" )

                        val bottomSheet = LoginBtmSheetFragment()
                        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                    }
                    R.id.menu_bottom_setting ->{
                        //changeFragment(SettingFragment(),"setting")

                        val bottomSheet = LoginBtmSheetFragment()
                        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
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