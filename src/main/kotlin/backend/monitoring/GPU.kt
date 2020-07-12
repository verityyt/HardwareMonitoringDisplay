package backend.monitoring

import backend.errors.NoComponentFoundException
import backend.errors.NoComponentSensorFoundException
import com.profesorfalken.jsensors.JSensors

object GPU {

    /**
     * Returns the current temperature of the GPU
     * !!! Takes 1000ms to return !!!
     */
    fun temperature(): Double? {

        val gpus = JSensors.get.components().gpus

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

    /**
     * Returns the device name and the series of the GPU
     * !!! Takes 1000ms to return !!!
     */
    fun name(): String {
        val gpus = JSensors.get.components().gpus
        val name = gpus.first().name
        return name
    }

}