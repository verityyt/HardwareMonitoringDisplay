package backend.monitoring

import com.profesorfalken.jsensors.JSensors

class GPU {

    val gpus = JSensors.get.components().gpus

    fun getTemperatures(): HashMap<String, String>? {

        if (gpus != null) {
            for (gpu in gpus) {
                if (gpu.sensors != null) {
                    val map = HashMap<String, String>()
                    for (temperature in gpu.sensors.temperatures) {
                        map[temperature.name] = "${temperature.value}°C"
                    }
                    return map
                }
            }
        }

        return null
    }

    fun getName(): String = gpus.first().name

}