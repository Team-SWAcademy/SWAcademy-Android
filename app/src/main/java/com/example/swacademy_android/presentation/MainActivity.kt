package com.example.swacademy_android.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityMainBinding
import com.example.swacademy_android.presentation.home.HomeFragment
import com.example.swacademy_android.util.BindingActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanIntentResult
import com.journeyapps.barcodescanner.ScanOptions
import dagger.hilt.android.AndroidEntryPoint

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

    private val qrCodeLauncher = registerForActivityResult(ScanContract()) {
        result : ScanIntentResult ->
        if (result.contents == null) {
            Toast.makeText (this, "Cancelled" , Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText (this, "Scanned:" + result.contents, Toast.LENGTH_LONG).show()
        }
    }

    private fun clickQrScannerBtn(){
        binding.fabCamera.setOnClickListener {
            val options = ScanOptions()
            options.apply{
                setOrientationLocked(false)
                setPrompt("화면에 QR코드를 스캔해주세요")
            }
            qrCodeLauncher.launch(options)
        }
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