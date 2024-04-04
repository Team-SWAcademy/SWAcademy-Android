package com.example.swacademy_android.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityMainBinding
import com.example.swacademy_android.util.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        initMainView()
        setBottomNavigation()
    }

    private fun setBottomNavigation() {

        with(binding){
            bottomNavigationMain.background = null
            bottomNavigationMain.menu.getItem(1).isEnabled = false
            fabCamera.setOnClickListener {
                startActivity(Intent(this@MainActivity,CameraActivity::class.java))
            }
        }

        binding.bottomNavigationMain.run {
            setOnItemSelectedListener {
                changeFragment(
                when(it.itemId) {
                    R.id.menu_home -> HomeFragment()
                    else -> MypageFragment()
                })
                true
            }
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }
    private fun initMainView() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null) {
            changeFragment(HomeFragment())
        }
    }
}