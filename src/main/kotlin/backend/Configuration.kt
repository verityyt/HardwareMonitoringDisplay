package backend

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File
import kotlin.system.exitProcess


object Configuration {

    val file = File("config.json")

    /**
     *
     * Styles:
     *   0: ReactiveCpuGpuCirclesStyleScreen
     *   1: NonreactiveCpuGpuCirclesStyleScreen
     *   2: ReactiveCpuRamGpuCirclesStyleScreen
     *
     */
    fun create() {
        if (!file.exists()) {
            val jsonObject = JSONObject()
            jsonObject.put("language", "en")
            jsonObject.put("style", "0")
            jsonObject.put("update_delay_ms", "1000")
            jsonObject.put("drive1_filter", "")
            jsonObject.put("drive2_filter", "")
            jsonObject.put("drive1_name", "")
            jsonObject.put("drive2_name", "")
            jsonObject.put("color_cpu", "#3867D6")
            jsonObject.put("color_gpu", "#8854D0")
            jsonObject.put("color_ram", "#2ECC71")
            jsonObject.put("color_drive1", "#E67E22")
            jsonObject.put("color_drive2", "#F1C40F")
            jsonObject.put("color_text", "#000000")
            jsonObject.put("color_bg", "#FFFFFF")

            val writer = file.writer()
            writer.write(jsonObject.toJSONString())
            writer.flush()
            writer.close()

            exitProcess(0)
        }
    }

    fun get(key: String): String {
        val jsonParser = JSONParser()
        val reader = file.reader()
        val jsonObject = jsonParser.parse(reader)
        reader.close()

        return (jsonObject as JSONObject)[key].toString()
    }

}