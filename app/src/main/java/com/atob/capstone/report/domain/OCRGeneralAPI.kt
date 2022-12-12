import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

object OCRGeneralAPI {

    @JvmStatic
    fun ocrGeneralAPI(imageFile: String): String{
        val apiURL = "https://02epycivzv.apigw.ntruss.com/custom/v1/19599/02a6ea8218911ceedb83149730f4fb80ae1e374664d4df28311117d0ba5f1a6f/general"
        val secretKey = "a2d0RE95TEtGU2dWdUNxcmJQRGF0YnZWUE5IZFFZQk4="

        val personalMobility = arrayListOf("Beam", "Lime")

        try {
            val url = URL(apiURL)
            val con = url.openConnection() as HttpURLConnection
            con.useCaches = false
            con.doInput = true
            con.doOutput = true
            con.readTimeout = 30000
            con.requestMethod = "POST"
            val boundary = "----" + UUID.randomUUID().toString().replace("-".toRegex(), "")
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=$boundary")
            con.setRequestProperty("X-OCR-SECRET", secretKey)
            val json = JSONObject()
            json.put("version", "V2")
            json.put("requestId", UUID.randomUUID().toString())
            json.put("timestamp", System.currentTimeMillis())
            val image = JSONObject()
            image.put("format", "jpg")
            image.put("name", "demo")
            val images = JSONArray()
            images.put(image)
            json.put("images", images)
            val postParams = json.toString()
            con.connect()       // 에러
            val wr = DataOutputStream(con.outputStream)
            val start = System.currentTimeMillis()
            val file = File(imageFile)
            writeMultiPart(wr, postParams, file, boundary)
            wr.close()
            val responseCode = con.responseCode
            val br: BufferedReader = if (responseCode == 200) {
                BufferedReader(InputStreamReader(con.inputStream))
            } else {
                BufferedReader(InputStreamReader(con.errorStream))
            }
            var inputLine: String?
            val response = StringBuffer()
            while (br.readLine().also { inputLine = it } != null) {
                response.append(inputLine)
            }
            br.close()

            // 회사 찾기
            for (mobility in personalMobility){
                if (mobility in response){
                    return mobility
                }
            }

        } catch (e: Exception) {
            println("error??? $e")
        }
        return "회사명이 잘 보이게 다시 촬영해주세요."
    }

    @Throws(IOException::class)
    private fun writeMultiPart(out: OutputStream,jsonMessage: String,file: File?,boundary: String) {
        val sb = StringBuilder()
        sb.append("--").append(boundary).append("\r\n")
        sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n")
        sb.append(jsonMessage)
        sb.append("\r\n")
        out.write(sb.toString().toByteArray(charset("UTF-8")))
        out.flush()
        if (file != null && file.isFile) {
            out.write("--$boundary\r\n".toByteArray(charset("UTF-8")))
            val fileString = StringBuilder()
            fileString
                .append("Content-Disposition:form-data; name=\"file\"; filename=")
            fileString.append(
                """
                    "${file.name}"
                    
                    """.trimIndent()
            )
            fileString.append("Content-Type: application/octet-stream\r\n\r\n")
            out.write(fileString.toString().toByteArray(charset("UTF-8")))
            out.flush()
            FileInputStream(file).use { fis ->
                val buffer = ByteArray(8192)
                var count: Int
                while (fis.read(buffer).also { count = it } != -1) {
                    out.write(buffer, 0, count)
                }
                out.write("\r\n".toByteArray())
            }
            out.write("--$boundary--\r\n".toByteArray(charset("UTF-8")))
        }
        out.flush()
    }
}