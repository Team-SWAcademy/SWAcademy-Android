package com.example.swacademy_android.presentation.mypage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.swacademy_android.R
import com.example.swacademy_android.data.model.response.MypageResponseDto
import com.example.swacademy_android.databinding.FragmentMypageBinding
import com.example.swacademy_android.util.BindingFragment
import com.github.mikephil.charting.data.BarEntry
import dagger.hilt.android.AndroidEntryPoint
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.formatter.LargeValueFormatter

@AndroidEntryPoint
class MypageFragment : BindingFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private val viewModel : MypageViewModel by viewModels()
    private var isEdit = false
    private var editNameValue = ""
    private val weekRental = ArrayList<BarEntry>()
    private val weekReturn = ArrayList<BarEntry>()
    private val monthRental = ArrayList<BarEntry>()
    private val monthReturn = ArrayList<BarEntry>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMypageData()
        addTextChangedEditName()
        setOnClickBtnEdit()
        setOnClickBtnMypage()
        setOnCheckedStatistics()
        getNickName()
    }

    private fun setMypageData(){
        viewModel.mypageResponse.observe(viewLifecycleOwner){
            with(binding){
                tvMypageUserName.text = "${it.nickname}님"
                tvMypageUserInfo.text = if (it.gender) {"남성"} else {"여성"}
                tvMultiuseStatePoint.text= "${it.currentPoint}P"
                tvMultiuseStateRentalCount.text= it.totalUseCount.toString()
                tvMultiuseStateReturnCount.text= it.totalReturnCount.toString()
                createDailyChart(it.dailyStatisticsResList)
                createMonthlyChart(it.monthlyStatisticsResList)
                drawChart()
            }
        }
    }

    private fun getNickName(){
        viewModel._editNickName.observe(viewLifecycleOwner){
            binding.tvMypageUserName.text= "${it}님"
        }
    }

    private fun createDailyChart(dailyStatisticsResList: List<MypageResponseDto.Result.DailyStatisticsResList>){
        dailyStatisticsResList.forEachIndexed { idx, data ->
            weekRental.add(BarEntry(0.8f + idx.toFloat(), data.useCount.toFloat()))
            weekReturn.add(BarEntry(1.2f + idx.toFloat(), data.returnCount.toFloat()))
        }
    }

    private fun createMonthlyChart(monthlyStatisticsResList: List<MypageResponseDto.Result.MonthlyStatisticsResList>){
        monthlyStatisticsResList.forEachIndexed { idx, data ->
            monthRental.add(BarEntry(0.8f + idx.toFloat(), data.useCount.toFloat()))
            monthReturn.add(BarEntry(1.2f + idx.toFloat(), data.returnCount.toFloat()))
        }
    }

    private fun setOnClickBtnEdit() {
        binding.ivEditUserNameBtn.setOnClickListener {
            binding.tvMypageUserName.visibility = View.INVISIBLE
            binding.ivEditUserNameBtn.visibility = View.INVISIBLE
            binding.etEditUserName.visibility = View.VISIBLE
            binding.clMypageBtnContent.text = "수정완료"
            isEdit = true
        }
    }

    private fun setOnClickBtnMypage() {
        binding.clMypageBtn.setOnClickListener {
            if (isEdit) {
                binding.tvMypageUserName.visibility = View.VISIBLE
                binding.ivEditUserNameBtn.visibility = View.VISIBLE
                binding.etEditUserName.visibility = View.INVISIBLE
                binding.clMypageBtnContent.text = "로그아웃"
                if(editNameValue.isNotEmpty()){
                    Log.e("hyeon", "notempty value = ${editNameValue}")
                    editUserNickName(editNameValue)
                }
            }
            else {
                Toast.makeText(context, "Log out", Toast.LENGTH_SHORT).show()
            }
            isEdit = !isEdit
        }
    }

    private fun editUserNickName(editNickName : String){
        Log.e("hyeon", "editUserName = ** ${editNickName}")
        viewModel.patchMypageUserName(editNickName)
        // TODO 수정완료 버튼 누르면 해당 함수가 실행됨 -> viewModel 감지해서 닉네임만 get 수정
    }

    private fun addTextChangedEditName() {
        binding.etEditUserName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                editNameValue = binding.etEditUserName.text.toString()
                Log.e("hyeon","editNameValue : ${editNameValue}")
                binding.clMypageBtn.isEnabled = editNameValue.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun setOnCheckedStatistics() {
        binding.rgMypageStatistic.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                binding.rbWeekStatistic.id -> {
                    binding.bcWeekChart.visibility = View.VISIBLE
                    binding.bcMonthChart.visibility = View.INVISIBLE
                }
                binding.rbMonthStatistic.id -> {
                    binding.bcWeekChart.visibility = View.INVISIBLE
                    binding.bcMonthChart.visibility = View.VISIBLE
                }
            }
        }
    }
    private fun drawChart() {
        drawChart(weekRental, weekReturn, "week")
        drawChart(monthRental, monthReturn, "month")
    }

    private fun drawChart(rentalData: ArrayList<BarEntry>, returnData: ArrayList<BarEntry>, chartType: String) {
        val chart: BarChart = if (chartType == "week") binding.bcWeekChart else binding.bcMonthChart

        chart.run {
            description.isEnabled = false
            setMaxVisibleValueCount(if (chartType == "week") 7 else 12)
            setPinchZoom(false)
            setDrawBarShadow(false)
            setDrawGridBackground(false)
            axisLeft.run {
                axisMinimum = 0f
                granularity = 1f
                setDrawLabels(true)
                setDrawGridLines(true)
                setDrawAxisLine(false)
                axisLineColor = ContextCompat.getColor(context, R.color.blue_D1E9FF)
                gridColor = ContextCompat.getColor(context, R.color.blue_D1E9FF)
                textColor = ContextCompat.getColor(context, R.color.black)
                textSize = 10f
            }
            xAxis.run {
                position = XAxis.XAxisPosition.BOTTOM
                granularity = 1f
                setDrawAxisLine(true)
                setDrawGridLines(false)
                textColor = ContextCompat.getColor(context, R.color.black)
                valueFormatter = if (chartType == "week") WeekXAxisFormatter() else MonthXAxisFormatter()
                textSize = 10f
                spaceMin = 0.2f
                spaceMax = 0.2f
            }
            axisRight.isEnabled = false
            setTouchEnabled(false)
            animateY(1000)
            legend.isEnabled = false
        }

        val rentalSet = BarDataSet(rentalData, "Dataset")
        rentalSet.color = ContextCompat.getColor(requireContext(), R.color.blue_3DA9FC)

        val returnSet = BarDataSet(returnData, "Dataset2")
        returnSet.color = ContextCompat.getColor(requireContext(), R.color.red_FA6A6A)

        val barData = BarData(rentalSet, returnSet)
        barData.setValueFormatter(LargeValueFormatter())
        barData.barWidth = 0.3f

        chart.run {
            this.data = barData
            setFitBars(true)
            invalidate()
        }
    }

    inner class WeekXAxisFormatter : ValueFormatter() {
        private val days = arrayOf("월", "화", "수", "목", "금", "토", "일")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt() - 1) ?: ""
        }
    }

    inner class MonthXAxisFormatter : ValueFormatter() {
        private val days = arrayOf("1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt() - 1) ?: ""
        }
    }
}