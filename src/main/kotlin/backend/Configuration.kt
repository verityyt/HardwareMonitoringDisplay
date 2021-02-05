package backend

import org.json.simple.JSONObject
import org.json.simple.parser.JSONParser
import java.io.File

object Configuration {

    val file = File("config.json")

    fun check() {
        val json = JSONParser().parse(file.readText()) as JSONObject

        if(json["alarm_cpu"] == null) {
            println("First start")

            json["language"] = "en"
            json["style"] = "0"
            json["update_delay_ms"] = "1000"
            json["color_cpu"] = "#3867D6"
            json["color_gpu"] = "#8854D0"
            json["color_ram"] = "#2ECC71"
            json["color_drive1"] = "#E67E22"
            json["color_drive2"] = "#F1C40F"
            json["alarm_sound"] = "false"
            json["alarm_cpu"] = "0"
            json["alarm_gpu"] = "0"
            json["posX"] = "0"
            json["posY"] = "0"
            json["fullscreen"] = "false"

            file.writeText(json.toJSONString())
        }
    }

    fun get(key: String): String {
        val jsonParser = JSONParser()
        val reader = file.reader()
        val jsonObject = jsonParser.parse(reader)
        reader.close()

        return (jsonObject as JSONObject)[key].toString()
    }

    fun set(key: String, value: String) {

        println("Setting $key to $value")

        val json = JSONParser().parse(file.readText()) as JSONObject

        json[key] = value

        file.writeText(json.toJSONString())
    }

}