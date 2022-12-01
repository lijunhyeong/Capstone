package com.atob.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        intentReportReceived()
    }

    private fun intentReportReceived(){
        findViewById<Button>(R.id.reportButton).setOnClickListener {
            val intent = Intent(this, ReportReceivedActivity::class.java)
            startActivity(intent)
        }
    }
}