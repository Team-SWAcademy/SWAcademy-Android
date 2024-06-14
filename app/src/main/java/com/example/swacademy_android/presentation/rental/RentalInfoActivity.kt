package com.example.swacademy_android.presentation.rental

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.swacademy_android.R
import com.example.swacademy_android.data.model.request.RentalMultiUseRequestDto
import com.example.swacademy_android.databinding.ActivityRentalInfoBinding
import com.example.swacademy_android.presentation.rental.viewmodel.RentalInfoViewModel
import com.example.swacademy_android.presentation.returns.ReturnDialogFragment
import com.example.swacademy_android.util.BindingActivity
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class RentalInfoActivity :
    BindingActivity<ActivityRentalInfoBinding>(R.layout.activity_rental_info) {
    private val viewModel by viewModels<RentalInfoViewModel>()
    var chosenType = -1
    var usedType: MutableList<Long> = mutableListOf()
    var latitude = 37.4500221
    var longitude = 126.653488

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("hyeon","rentalInfo")
        getLocationData()
        onClickRentalBtn()
        viewLocationData()
        loadMap()
    }

    private fun choiceMultiUseCategory() {
        val choiceBindings = arrayOf(
            binding.clRentalChoiceCup,
            binding.clRentalChoiceBowl,
            binding.clRentalChoiceCutlery,
            binding.clRentalChoiceBox
        )
        val rentalBtn = binding.clRentalBtn

        choiceBindings.forEachIndexed { idx, choice ->
            if (usedType.contains(idx.toLong() + 1)) {
                choice.setOnClickListener {
                    Log.e("hyeon","click 됨")
                    if (chosenType != -1) {
                        Log.e("hyeon","실제로 값이 눌러짐")
                        choiceBindings[chosenType - 1].setBackgroundResource(R.drawable.shape_white_fill_d1e9ff_stroke_r20)
                    } else {
                        rentalBtn.setBackgroundResource(R.drawable.shape_d1e9ff_fill_e9f1ff_stroke_r50)
                    }

                    choice.setBackgroundResource(R.drawable.shape_white_fill_3da9fc_stroke_r20)
                    chosenType = idx + 1
                }
            } else {
                choice.setBackgroundResource(R.drawable.shape_f5f5f5_fill_dadada_stroke_r20)
            }
        }
    }

    private fun viewLocationData() {
        viewModel.rentalLocationResponse.observe(this) {
            with(binding) {
                tvRentalLocation.text = "${it.locationName} ${it.locationAddress}"
                for (i in 0 until it.multiUseContainerIdList.size) {
                    usedType.add(it.multiUseContainerIdList[i])
                }
                latitude = if (it.latitude != 0.0) it.latitude else latitude
                longitude = if (it.longitude != 0.0) it.longitude else longitude
                Log.e("hyeon","usedType : ${usedType}")
                choiceMultiUseCategory()
            }
        }
    }

    private fun getLocationData() {
        val locationId = intent.getIntExtra("locationId", -1)
        val point = intent.getIntExtra("point", -1)
        viewModel.getRentalLocationData(locationId, point)
    }

    private fun onClickRentalBtn() {
        val locationId = intent.getIntExtra("locationId", -1)
        val point = intent.getIntExtra("point", -1)
        binding.clRentalBtn.setOnClickListener {
            val rentalData = RentalMultiUseRequestDto(locationId, point, chosenType)
            showRentalDialogFragment(rentalData)
        }
    }

    private fun showRentalDialogFragment(rentalData: RentalMultiUseRequestDto) {
        RentalDialogFragment(rentalData).apply {
            show(supportFragmentManager, "RentalDialogFragment")
        }
    }

    private fun loadMap() {
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
                val style = p0.labelManager?.addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.ic_label_bluebottle)))
                val options = LabelOptions.from(LatLng.from(latitude, longitude)).setStyles(style)
                val layer = p0.labelManager?.layer
                layer?.addLabel(options)
            }

            override fun getPosition(): LatLng {
                // 지도 시작 시 위치 좌표를 설정
                return LatLng.from(latitude, longitude)
            }

            override fun getZoomLevel(): Int {
                return 17
            }
        })
    }
}