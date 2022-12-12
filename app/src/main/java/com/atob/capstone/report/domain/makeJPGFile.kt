package com.atob.capstone.report.domain

import android.content.Context
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class makeJPGFile {

    @RequiresApi(Build.VERSION_CODES.N)
    fun newJpgFileName() : String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.jpg"
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun saveBitmapAsJPGFile(context: Context, bitmap: Bitmap) : String {
        val path = File(context.filesDir, "image")
        if(!path.exists()){
            path.mkdirs()
        }
        val file = File(path, newJpgFileName())
        var imageFile: OutputStream? = null

        try{
            file.createNewFile()
            imageFile = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageFile)
            imageFile.close()
            return file.absolutePath
        }catch (e: Exception){
//            Toast.makeText(context, "error", Toast.LENGTH_LONG).show()
            Log.d("saveBitmapAsJPGFile ???","$e")
        }
        return ""
    }


}