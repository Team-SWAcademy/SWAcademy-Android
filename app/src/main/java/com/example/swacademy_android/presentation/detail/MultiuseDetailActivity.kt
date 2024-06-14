package com.example.swacademy_android.presentation.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import coil.load
import com.example.swacademy_android.R
import com.example.swacademy_android.data.model.response.DetailMultiUseResponseDto
import com.example.swacademy_android.databinding.ActivityMultiuseDetailBinding
import com.example.swacademy_android.presentation.camera.CameraActivity
import com.example.swacademy_android.util.BindingActivity
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MultiuseDetailActivity :
    BindingActivity<ActivityMultiuseDetailBinding>(R.layout.activity_multiuse_detail) {

    private val viewModel by viewModels<MultiUseDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setOnclickBtnReturn()
        getMultiUseDetail()
        setMultiUseDetail()
    }

    private fun getMultiUseDetail() {
        val multiUseAt = intent.getStringExtra("useAt")
        if (multiUseAt != null) {
            viewModel.getMultiUseDetail(multiUseAt)
        }
    }

    private fun setMultiUseDetail() {
        with(binding) {
            viewModel.detailResponseDto.observe(this@MultiuseDetailActivity) {
                tvMultiuseDetailLocationContent.text = "${it.locationName} ${it.locationAddress}"
                tvMultiuseDetailDateContent.text = it.useAt
                tvMultiuseDetailPointContent.text = "${it.point}P"
                ivMultiuseCategory.load(setMultiUseCategory(it.multiUseContainerId))

                loadMap(it.getReturnResList, it.multiUseContainerId)
            }
        }
    }

    private fun setMultiUseCategory(multiUseCategory: Int): Int {
        when (multiUseCategory) {
            1 -> {
                return R.drawable.ic_multiuse_detail_type_cup
            }

            2 -> {
                return R.drawable.ic_multiuse_detail_type_bowl
            }

            3 -> {
                return R.drawable.ic_multiuse_detail_type_box
            }

            else -> {
                return R.drawable.ic_multiuse_detail_type_cutlery
            }
        }
    }

    private fun setOnclickBtnReturn() {
        binding.clMultiuseDetailReturnBtn.setOnClickListener {
            startActivity(Intent(this@MultiuseDetailActivity, CameraActivity::class.java)
                .apply{
                    putExtra("useAt",viewModel.detailResponseDto.value?.useAt)
                })
        }
    }

    private fun loadMap(
        returnResList: List<DetailMultiUseResponseDto.DetailResult.ReturnRes>,
        multiUseContainerId: Int
    ) {
        var returnMap = binding.mvMultiuseDetailReturnMap
        returnMap.start(object : MapLifeCycleCallback() {
            override fun onMapDestroy() {
                Log.d("ej destroy", "map destroy")
            }

            override fun onMapError(p0: Exception?) {
                Log.d("ej error", p0.toString())
            }
        }, object : KakaoMapReadyCallback() {

            // 임시 반납 위치 데이터
            val dataList : List<Map<String, Double>> = listOf(
                mapOf("latitude" to 37.4500221, "longitude" to 126.653488),
                mapOf("latitude" to 37.4511651, "longitude" to 126.6517204),
            )

            override fun onMapReady(p0: KakaoMap) {
                Log.d("ej ready", "map ready")

                returnResList.forEach { data ->
                    val latitude: Double = (data.latitude as? Double) ?: 0.0
                    val longitude: Double = (data.longitude as? Double) ?: 0.0

                    if (latitude == 0.0 && longitude == 0.0) {  // 서버에 위도 및 경도 정보 0.0으로 저장된 경우
                        addLabelTopMap(p0, 37.4500221, 126.653488, multiUseContainerId)
                        addLabelTopMap(p0, 37.4511651, 126.6517204, multiUseContainerId)
                    }
                    else {  // 서버에 위도 및 경도 정보 저장된 경우
                        addLabelTopMap(p0, latitude, longitude, multiUseContainerId)
                    }
                }
            }

            private fun addLabelTopMap(p0: KakaoMap, latitude: Double, longitude: Double, multiUseContainerId: Int) {
                // 반납 유형에 따라 다른 아이콘 지정
                val style = p0.labelManager?.addLabelStyles(LabelStyles.from(LabelStyle.from(setLabelCategory(multiUseContainerId))))
                val options = LabelOptions.from(LatLng.from(latitude, longitude)).setStyles(style)
                val layer = p0.labelManager?.layer
                layer?.addLabel(options)
            }

            private fun setLabelCategory(multiUseCategory: Int): Int {
                when (multiUseCategory) {
                    1 -> {
                        return R.drawable.ic_label_cup
                    }

                    2 -> {
                        return R.drawable.ic_label_bowl
                    }

                    3 -> {
                        return R.drawable.ic_label_box
                    }

                    else -> {
                        return R.drawable.ic_label_cutlery
                    }
                }
            }

            override fun getPosition(): LatLng {
                // 지도 시작 시 위치 좌표 설정 함수
                var startLatitude = 0.0
                var startLongitude = 0.0

                returnResList.forEach { data ->
                    startLatitude += (data.latitude as? Double) ?: 0.0
                    startLongitude += (data.longitude as? Double) ?: 0.0
                }
                startLatitude /= returnResList.size
                startLongitude /= returnResList.size

                if (startLatitude == 0.0) startLatitude = (37.4500221 + 37.4511651) / 2.0
                if (startLongitude == 0.0) startLongitude = (126.653488 + 126.6517204) / 2.0

                return LatLng.from(startLatitude, startLongitude)
            }

            override fun getZoomLevel(): Int {
                return 17
            }
        })
    }
}