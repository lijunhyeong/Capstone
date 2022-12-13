import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

object OCRGeneralAPI {

    @JvmStatic
    fun ocrGeneralAPI(imageFile: String): String{
        val apiURL = "YOUR_API_URL"
        val secretKey = "YOUR_SECRET_KEY"

        val personalMobility = arrayListOf("Beam", "Lime",  "고고씽", "DART”, “SWING”, “deer”, “Lime”, “beam”, “씽씽”, “ALPACA”, “WIND”, “elecle”, “ZET”, “GCOOTER”, “G.BIKE”, “KICKGOING”, “플라워로드”, “FLOWEROAD", "HIKICK”, “이브이패스”, “mate”, “mate mercane”, “kakao T bike”, “TAZO”, “쓩”, “백원킥보드”, “KICKS”, “neuron”, “BIRD”, “MARY", "MARY bike")

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
