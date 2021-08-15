package com.yoonji.coupangeatsproject.src.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ActivityMainBinding
import com.yoonji.coupangeatsproject.src.main.history.HistoryFragment
import com.yoonji.coupangeatsproject.src.main.home.HomeFragment
import com.yoonji.coupangeatsproject.src.main.like.LikeFragment
import com.yoonji.coupangeatsproject.src.main.search.SearchFragment
import com.yoonji.coupangeatsproject.src.main.setting.SettingFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btmNaviMain.run{
            setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.menu_bottom_home ->{
                        changeFragment(HomeFragment())
                    }
                    R.id.menu_bottom_search ->{
                        changeFragment(SearchFragment())
                    }
                    R.id.menu_bottom_like ->{
                        changeFragment(LikeFragment())
                    }
                    R.id.menu_bottom_history ->{
                        changeFragment(HistoryFragment())
                    }
                    R.id.menu_bottom_setting ->{
                        changeFragment(SettingFragment())
                    }
                }
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