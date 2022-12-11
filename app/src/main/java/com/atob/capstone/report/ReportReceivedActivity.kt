package com.atob.capstone.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.atob.capstone.R

class ReportReceivedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_received)

        val ll = intent.getStringExtra("uLatitude")
        val lolo = intent.getStringExtra("uLongitude")
        Log.d("??uLatitude---", ll!!)
        Log.d("??uLongitude---", lolo!!)

    }


}