package com.atob.capstone.report.view

import OCRGeneralAPI.ocrGeneralAPI
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.atob.capstone.ReportFinishActivity
import com.atob.capstone.databinding.ActivityReportReceivedBinding
import com.atob.capstone.report.domain.CheckPermission
import com.atob.capstone.report.domain.CheckPermission.Companion.REQUEST_IMAGE_CAPTURE
import com.atob.capstone.report.domain.CurrentDate
import com.atob.capstone.report.domain.makeJPGFile
import nl.bryanderidder.themedtogglebuttongroup.ThemedButton
import java.util.*


class ReportReceivedActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReportReceivedBinding

    // 자전거, 킥보드 선택
    private var category = mutableListOf<ThemedButton>()
    // 현재 일시
    private var currentDate: String?=null
    // 킥보드나 자전거 회사명으로 변경
    private var companyName : String ?= null


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportReceivedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initSetting()
        // 자전거, 킥보드 선택
        selectCategory()
        // 사진 촬영
        takePhoto()
        // 셀프 처리
        doneSelf()
        // 운영팀 출동
        doneMove()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initSetting(){
        // 신고 일시
        binding.currentDate.text = CurrentDate().currentDate()
        currentDate = binding.currentDate.text.toString()
        // 카메라 권환 요청
        CheckPermission().checkPermission(this)
    }


    // 자전거, 킥보드 선택
    private fun selectCategory(){
        category = binding.category.selectedButtons
    }

    // 사진 촬영
    private fun takePhoto(){
        binding.camera.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_IMAGE_CAPTURE) {
            val count = grantResults.count { it == PackageManager.PERMISSION_DENIED }
            if(count != 0) {
                Toast.makeText(applicationContext, "권한을 동의해주세요.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    // 사진 업로드
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap

            binding.imageView.setImageBitmap(imageBitmap)

            // JPG 파일 만들기
            val uri = makeJPGFile().saveBitmapAsJPGFile(this, imageBitmap)

            var check = false
            Thread {
                // network 동작, 인터넷에서 xml을 받아오는 코드
                companyName = ocrGeneralAPI(uri)
                check = true
            }.start()

            val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    if (check) {
                        runOnUiThread(Runnable {
                            //소스
                            binding.company.text = "! $companyName"
                        })
                        timer.cancel()
                    }
                }
            }, 0, 1000)
        }

    }


    // 셀프 처리
    private fun doneSelf(){
        binding.donSelf.setOnClickListener {
            doneSetting("셀프")
        }
    }

    // 운영팀 처리
    private fun doneMove(){
        binding.doneMove.setOnClickListener {
            doneSetting("운영팀 출동")
        }
    }

    private fun doneSetting(processingMethod: String){
        if(companyName == null || currentDate == null || category.isEmpty() || "다시 촬영" in companyName!!){
            // 기재하지 않은 게 있습니다.
            Toast.makeText(this, "모든 항목을 채워주세요.", Toast.LENGTH_SHORT).show()
        }else{
            val intentReportFinishActivity = Intent(this, ReportFinishActivity::class.java)
            intentReportFinishActivity.putExtra("processingMethod", processingMethod)       // 처리 방법
            intentReportFinishActivity.putExtra("companyName", "$companyName")       // 카메라로 찍은 회사명
            intentReportFinishActivity.putExtra("currentDate", "$currentDate")       // 신고 일시
            intentReportFinishActivity.putExtra("selectCategory", category[0].text)       // 카테고리
            intentReportFinishActivity.putExtra("uLatitude", intent.getStringExtra("uLatitude").toString())       // 위도
            intentReportFinishActivity.putExtra("uLongitude", intent.getStringExtra("uLongitude").toString())       // 경도
            startActivity(intentReportFinishActivity)
        }
    }

}