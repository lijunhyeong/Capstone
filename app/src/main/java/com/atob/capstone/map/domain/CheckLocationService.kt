package com.atob.capstone.map.domain

import android.content.Context
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService

class CheckLocationService {

    // GPS가 켜져있는지 확인
    fun checkLocationService(context: Context): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

}