package com.atob.capstone.report.domain

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CurrentDate {

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH시 mm분 ss초")
        return current.format(formatter)
    }

}