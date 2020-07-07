package backend.monitoring

import backend.errors.NoComponentFoundException
import backend.errors.NoComponentSensorFoundException
import com.profesorfalken.jsensors.JSensors

class GPU {

    val gpus = JSensors.get.components().gpus
    val name = gpus.first().name

    fun temperature(): Double? {
        if (gpus != null) {
            for (gpu in gpus) {
                if (gpu.sensors != null) {
                    return gpu.sensors.temperatures.first().value
                }else {
                    throw NoComponentSensorFoundException("GPU")
                }
            }
        }else {
            throw NoComponentFoundException("GPU")
        }

        return null
    }

}