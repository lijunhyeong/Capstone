package com.atob.capstone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.atob.capstone.map.view.MapActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // intentReportReceived()
        intentReportCategory()

        //getHashKey()
    }

    private fun intentReportCategory(){
        findViewById<Button>(R.id.homeButton).setOnClickListener {
            val intentReportCategory = Intent(this, MapActivity::class.java)
            startActivity(intentReportCategory)
        }
    }


}