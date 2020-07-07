package backend.monitoring

import backend.errors.NoComponentFoundException
import backend.errors.NoComponentSensorFoundException
import com.profesorfalken.jsensors.JSensors

class CPU {

    val cpus = JSensors.get.components().cpus

    fun getTemperatures(): HashMap<String, String>? {

        if (cpus != null) {
            for (cpu in cpus) {
                if (cpu.sensors != null) {
                    val map = HashMap<String, String>()
                    for (temperature in cpu.sensors.temperatures) {
                        map[temperature.name] = "${temperature.value}°C"
                    }
                    return map
                }else {
                    throw NoComponentSensorFoundException("CPU")
                }
            }
        }else {
            throw NoComponentFoundException("CPU")
        }

        return null
    }

    fun getName(): String = cpus.first().name

}