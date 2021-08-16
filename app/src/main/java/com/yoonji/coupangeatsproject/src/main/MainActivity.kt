package com.yoonji.coupangeatsproject.src.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.yoonji.coupangeatsproject.BaseActivity
import com.yoonji.coupangeatsproject.R
import com.yoonji.coupangeatsproject.databinding.ActivityMainBinding
import com.yoonji.coupangeatsproject.src.main.home.HomeFragment
import com.yoonji.coupangeatsproject.src.main.like.LikeFragment
import com.yoonji.coupangeatsproject.src.main.search.SearchFragment

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
                        //changeFragment(HistoryFragment())
                        val loginBtmSheetDlgFragment: LoginBtmSheetFragment = LoginBtmSheetFragment {
                            when (it) {
                                0 -> {
                                    val intent = Intent(context, SignUpActivity::class.java)
                                    startActivity(intent)
                                }
                                1 -> {
                                    val intent = Intent(context, SignUpActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                        loginBtmSheetDlgFragment.show(supportFragmentManager, loginBtmSheetDlgFragment.tag)
                    }
                    R.id.menu_bottom_setting ->{
                        //changeFragment(SettingFragment())
                        val loginBtmSheetDlgFragment: LoginBtmSheetFragment = LoginBtmSheetFragment {
                            when (it) {
                                0 -> {
                                    val intent = Intent(context, SignUpActivity::class.java)
                                    startActivity(intent)
                                }
                                1 -> {
                                    val intent = Intent(context, SignUpActivity::class.java)
                                    startActivity(intent)
                                }
                            }
                        }
                        loginBtmSheetDlgFragment.show(supportFragmentManager, loginBtmSheetDlgFragment.tag)

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