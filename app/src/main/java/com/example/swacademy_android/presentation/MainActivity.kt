package com.example.swacademy_android.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityMainBinding
import com.example.swacademy_android.presentation.home.HomeFragment
import com.example.swacademy_android.presentation.point.PointFragment
import com.example.swacademy_android.presentation.rental.RentalChoiceMultiUseActivity
import com.example.swacademy_android.presentation.store.StoreFragment
import com.example.swacademy_android.util.BindingActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var context: Context
    private val qrCodeIntentIntegrator = IntentIntegrator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        initMainView()
        setBottomNavigation()
        clickQrScannerBtn()
    }

    private fun clickQrScannerBtn(){
        binding.fabCamera.setOnClickListener {
           startActivity(Intent(this@MainActivity,RentalChoiceMultiUseActivity::class.java))
        }
    }

    private fun setBottomNavigation() {

        with(binding){
            bottomNavigationMain.background = null
            bottomNavigationMain.menu.getItem(1).isEnabled = false
        }

        binding.bottomNavigationMain.run {
            setOnItemSelectedListener {
                changeFragment(
                when(it.itemId) {
                    R.id.menu_home -> HomeFragment()
                    R.id.menu_mypage -> MypageFragment()
                    R.id.menu_point -> PointFragment()
                    R.id.menu_store -> StoreFragment()
                    else -> HomeFragment()
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