package backend.monitoring

import com.profesorfalken.jsensors.JSensors
import oshi.SystemInfo

object CPU {

    val sensors = SystemInfo().hardware.sensors

    /**
     * Returns the current temperature of the CPU
     * !!! Takes 1000ms to return !!!
     */
    fun temperature(): Double? {
        val temp = sensors.cpuTemperature

        if(HardwareMonitoringDisplay.ohmProcess.isAlive) {
            return temp
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