package com.atob.capstone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.atob.capstone.databinding.ActivityReportFinishBinding

class ReportFinishActivity: AppCompatActivity() {

    private lateinit var binding: ActivityReportFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportFinishBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        initSetting()
        getData()
        intentHome()

    }

    private fun initSetting(){
        binding.textView.text = settingTextView()
    }

    private fun settingTextView():String{
        val processingMethod = intent.getStringExtra("processingMethod")
        return if (processingMethod== "운영팀 출동") "신고접수가\n완료되었습니다."
            else "신고접수가\n완료되었습니다.\n1분간 잠금을 해제합니다."
    }

    private fun getData(){
        val processingMethod = intent.getStringExtra("processingMethod")
        val companyName = intent.getStringExtra("companyName")
        val currentDate = intent.getStringExtra("currentDate")
        val selectCategory = intent.getStringExtra("selectCategory")
        val uLatitude = intent.getStringExtra("uLatitude")      // 위도
        val uLongitude = intent.getStringExtra("uLongitude")    // 경도

        Log.d("일시 ---> ","$currentDate")
        Log.d("분류 ---> ","$selectCategory")
        Log.d("위도 ---> ","$uLatitude")
        Log.d("경도 ---> ","$uLongitude")
        Log.d("회사명 ---> ","$companyName")
        Log.d("처리 방법 ---> ","$processingMethod")
    }

    private fun intentHome(){
        findViewById<Button>(R.id.intentHome).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
            startActivity(intent)
        }
    }

}