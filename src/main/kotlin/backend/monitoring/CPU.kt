package backend.monitoring

import backend.NotificationManager
import backend.errors.NoComponentFoundException
import backend.errors.NoComponentSensorFoundException
import com.profesorfalken.jsensors.JSensors

object CPU {

    /**
     * Returns the current temperature of the CPU
     * !!! Takes 1000ms to return !!!
     */
    fun temperature(): Double? {

        val cpus = JSensors.get.components().cpus

        if (cpus != null) {
            for (cpu in cpus) {
                if (cpu.sensors != null) {
                    val value = cpu.sensors.temperatures.first().value

                    Thread {
                        NotificationManager.checkCpuTemp(value)
                    }.start()

                    return value
                }else {
                    throw NoComponentSensorFoundException("CPU")
                }
            }
        }else {
            throw NoComponentFoundException("CPU")
        }

        return null
    }

    /**
     * Returns the device name and the series of the CPU
     * !!! Takes 1000ms to return !!!
     */
    fun name(): String {
        val cpus = JSensors.get.components().cpus
        val name = cpus.first().name
        return name
    }

}