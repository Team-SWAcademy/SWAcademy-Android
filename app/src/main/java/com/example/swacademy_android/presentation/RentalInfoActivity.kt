package com.example.swacademy_android.presentation

import android.os.Bundle
import android.util.Log
import com.example.swacademy_android.R
import com.example.swacademy_android.databinding.ActivityRentalInfoBinding
import com.example.swacademy_android.util.BindingActivity
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class RentalInfoActivity : BindingActivity<ActivityRentalInfoBinding>(R.layout.activity_rental_info) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var usedType = arrayOf(0, 1, 2)

        var chosenType = -1
        val choiceBindings = arrayOf(binding.clRentalChoiceCup, binding.clRentalChoiceBowl, binding.clRentalChoiceCutlery, binding.clRentalChoiceBox)
        val rentalBtn = binding.clRentalBtn

        choiceBindings.forEachIndexed { idx, choice ->
            if(usedType.contains(idx)) {
                choice.setOnClickListener {
                    if (chosenType != -1) {
                        choiceBindings[chosenType].setBackgroundResource(R.drawable.shape_white_fill_d1e9ff_stroke_r20)
                    } else {
                        rentalBtn.setBackgroundResource(R.drawable.shape_d1e9ff_fill_e9f1ff_stroke_r50)
                    }

                    choice.setBackgroundResource(R.drawable.shape_white_fill_3da9fc_stroke_r20)
                    chosenType = idx
                }
            }
            else {
                choice.setBackgroundResource(R.drawable.shape_f5f5f5_fill_dadada_stroke_r20)
            }
        }


        var rentalMap = binding.mvRentalLocationMap
        rentalMap.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                Log.d("ej destroy", "map destroy")
            }

            override fun onMapError(p0: Exception?) {
                Log.d("ej error", p0.toString())
            }
        }, object : KakaoMapReadyCallback() {
            override fun onMapReady(p0: KakaoMap) {
                Log.d("ej ready", "map ready")
            }
        })

    }

}