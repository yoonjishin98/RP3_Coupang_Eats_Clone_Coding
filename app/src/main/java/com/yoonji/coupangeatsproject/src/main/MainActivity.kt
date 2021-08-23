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
                        supportFragmentManager.popBackStackImmediate("home", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        changeFragment(HomeFragment(),"home")

                    }
                    R.id.menu_bottom_search ->{
                        supportFragmentManager.popBackStackImmediate("search", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        changeFragment(SearchFragment(),"search")
                    }
                    R.id.menu_bottom_like ->{
                        supportFragmentManager.popBackStackImmediate("like", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        supportFragmentManager.beginTransaction().addToBackStack("like")
                        val intent = Intent(context, LikeActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.menu_bottom_history ->{
                        //supportFragmentManager.popBackStackImmediate("home", FragmentManager.POP_BACK_STACK_INCLUSIVE)
                        //changeFragment(HistoryFragment(),"history")
//                        val jwt = ApplicationClass.sSharedPreferences.getString("jwt", "")
//                        Log.d(TAG, "onCreate: $jwt" )


                        val bottomSheet = LoginBtmSheetFragment()
                        bottomSheet.show(supportFragmentManager, bottomSheet.tag)
                    }
                    R.id.menu_bottom_setting ->{
                        //supportFragmentManager.popBackStackImmediate("home", FragmentManager.POP_BACK_STACK_INCLUSIVE)
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

    private fun changeFragment(fragment: Fragment, tag:String) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(tag)
            .replace(R.id.main_frm, fragment, tag)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        updateBottomMenu(binding.btmNaviMain)
    }

    fun updateBottomMenu(navigation: BottomNavigationView) {
        val tag1: Fragment? = supportFragmentManager.findFragmentByTag("home")
        val tag2: Fragment? = supportFragmentManager.findFragmentByTag("serach")
        val tag3: Fragment? = supportFragmentManager.findFragmentByTag("history")
        val tag4: Fragment? = supportFragmentManager.findFragmentByTag("setting")
        val tag5: Fragment? = supportFragmentManager.findFragmentByTag("like")

        if(tag1 != null ) {
            navigation.menu.findItem(R.id.menu_bottom_home).isChecked = true
            Log.d("TAG", "updateBottomMenu: home")
        }
        else if(tag2 != null) {
            navigation.menu.findItem(R.id.menu_bottom_search).isChecked = true
            Log.d("TAG", "updateBottomMenu: search")
        }
        else if(tag3 != null ){
            navigation.menu.findItem(R.id.menu_bottom_history).isChecked = true
            Log.d("TAG", "updateBottomMenu: history")
        }
        else if(tag4 != null && tag4.isVisible)
            navigation.menu.findItem(R.id.menu_bottom_setting).isChecked = true
        else if(tag5 != null && tag5.isVisible){
            var d:Boolean = supportFragmentManager.popBackStackImmediate("like", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            Log.d("TAG", "updateBottomMenu: $d")
        }

    }



}