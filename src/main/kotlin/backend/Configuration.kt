package backend

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File


object Configuration {

    val file = File("config.json")

    fun create() {
        if (!file.exists()) {
            val jsonObject = JSONObject()
            jsonObject.put("language", "en")
            jsonObject.put("style", "0")
            jsonObject.put("update_delay_ms", "1000")
            jsonObject.put("color_1", "#3867D6")
            jsonObject.put("color_2", "#8854D0")
            jsonObject.put("color_bg", "#FFFFFF")

            val writer = file.writer()
            writer.write(jsonObject.toJSONString())
            writer.flush()
            writer.close()

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