package com.atob.capstone.report.domain

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat

class CheckPermission {

    // 카메라 권환
    companion object{
        val REQUEST_IMAGE_CAPTURE = 1
    }

    // 카메라 권환
    fun checkPermission(activity: Activity) {
        val permission = mutableMapOf<String, String>()
        permission["camera"] = Manifest.permission.CAMERA
        var denied = permission.count { ContextCompat.checkSelfPermission(activity, it.value)  == PackageManager.PERMISSION_DENIED }
        if(denied > 0) activity.requestPermissions(permission.values.toTypedArray(), REQUEST_IMAGE_CAPTURE)
    }



}