package com.example.swacademy_android.presentation.rental

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityRentalChoiceMultiuseBinding
import com.example.swacademy_android.util.BindingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RentalChoiceMultiUseActivity :
    BindingActivity<ActivityRentalChoiceMultiuseBinding>(R.layout.activity_rental_choice_multiuse) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        choiceMultiuseCategory()
    }

    private fun choiceMultiuseCategory() {
        with(binding) {
            clRentalChoiceCup.setOnClickListener {
                Log.e("hyeon","cup 선택")
                startActivity(Intent(this@RentalChoiceMultiUseActivity, CameraActivity::class.java).apply{
                    putExtra("category",1)
                    putExtra("choice", true)
                })
            }
            clRentalChoiceBowl.setOnClickListener {
                Log.e("hyeon","bowl 선택")
                startActivity(Intent(this@RentalChoiceMultiUseActivity, CameraActivity::class.java).apply{
                    putExtra("category",2)
                    putExtra("choice", true)
                })
            }
            clRentalChoiceBox.setOnClickListener {
                Log.e("hyeon","box 선택")
                startActivity(Intent(this@RentalChoiceMultiUseActivity, CameraActivity::class.java).apply{
                    putExtra("category",3)
                    putExtra("choice", true)
                })
            }
            clRentalChoiceCutlery.setOnClickListener {
                Log.e("hyeon","cutlery 선택")
                startActivity(Intent(this@RentalChoiceMultiUseActivity, CameraActivity::class.java).apply{
                    putExtra("category",4)
                    putExtra("choice", true)
                })
            }
        }
    }

}