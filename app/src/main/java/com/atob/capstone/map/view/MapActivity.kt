package com.atob.capstone.map.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.atob.capstone.report.view.ReportReceivedActivity
import com.atob.capstone.databinding.ActivityMapBinding
import com.atob.capstone.map.domain.CheckLocationService
import net.daum.mf.map.api.MapView

class MapActivity : AppCompatActivity()  {

    private lateinit var binding: ActivityMapBinding
    private val ACCESS_FINE_LOCATION = 1000     // Request Code


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // 초기 세팅
        initSetting()
        // 신고 페이지 이동
        intentReportReceived()
        // 내 위치
        myPositionButton()
    }

    // 초기 세팅
    private fun initSetting(){
        getMyPosition()
    }

    // 신고 페이지 이동
    @SuppressLint("MissingPermission")
    private fun intentReportReceived(){
        binding.intentReport.setOnClickListener {
            // 현재 위도, 경도 가져오기
            val lm: LocationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val userNowLocation: Location =
                lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)!!
            val uLatitude = userNowLocation.latitude       // 위도
            val uLongitude = userNowLocation.longitude      // 경도

            val intent = Intent(this, ReportReceivedActivity::class.java)
            intent.putExtra("uLatitude", "$uLatitude")
            intent.putExtra("uLongitude", "$uLongitude")

            startActivity(intent)
        }
    }

    // 내 위치 이동
    private fun myPositionButton(){
        binding.myPosition.setOnClickListener {
            getMyPosition()
        }
    }

    // 내 위치 불러오기
    private fun getMyPosition(){
        if (CheckLocationService().checkLocationService(this)) {
            // GPS가 켜져있을 경우
            permissionCheck()
            binding.mapView.setZoomLevel(1, true)
        } else {
            // GPS가 꺼져있을 경우
            Toast.makeText(this, "GPS를 켜주세요", Toast.LENGTH_SHORT).show()
        }
    }


    // 위치 권한 확인
    private fun permissionCheck(): Boolean {
        val preference = getPreferences(MODE_PRIVATE)
        val isFirstCheck = preference.getBoolean("isFirstPermissionCheck", true)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 없는 상태
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // 권한 거절 (다시 한 번 물어봄)
                val builder = AlertDialog.Builder(this)
                builder.setMessage("현재 위치를 확인하시려면 위치 권한을 허용해주세요.")
                builder.setPositiveButton("확인") { _, _ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        ACCESS_FINE_LOCATION
                    )
                }
                builder.setNegativeButton("취소") { _, _ ->

                }
                builder.show()
            } else {
                if (isFirstCheck) {
                    // 최초 권한 요청
                    preference.edit().putBoolean("isFirstPermissionCheck", false).apply()
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        ACCESS_FINE_LOCATION
                    )
                } else {
                    // 다시 묻지 않음 클릭 (앱 정보 화면으로 이동)
                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("현재 위치를 확인하시려면 설정에서 위치 권한을 허용해주세요.")
                    builder.setPositiveButton("설정으로 이동") { _, _ ->
                        val intent = Intent(
                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                            Uri.parse("package:$packageName")
                        )
                        startActivity(intent)
                    }
                    builder.setNegativeButton("취소") { _, _ ->

                    }
                    builder.show()
                }
            }
        } else {
            // 권한이 있는 상태
            startTracking()
        }
        return false
    }

    // 위치추적 시작
    private fun startTracking() {
        binding.mapView.currentLocationTrackingMode =
            MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }


}