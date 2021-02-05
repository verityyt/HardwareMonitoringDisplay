package backend.monitoring

import backend.Logger
import backend.errors.NoComponentFoundException
import backend.errors.NoComponentSensorFoundException
import com.profesorfalken.jsensors.JSensors

object DRIVE {

    val driveNames = mutableListOf<String>()

    /**
     * Returns the current temperature of the CPU
     * !!! Takes 1000ms to return !!!
     */
    fun temperature(searchFor: String): Double? {
        val drives = JSensors.get.components().disks

        if (drives != null) {
            for (drive in drives) {
                if (drive.sensors != null) {
                    if (drive.name.contains(searchFor, true))
                        return drive.sensors.temperatures.first().value
                } else {
                    throw NoComponentSensorFoundException("DRIVE")
                }
            }
        } else {
            throw NoComponentFoundException("DRIVE")
        }

        return null
    }

    /**
     * Returns the device name and the series of the CPU
     * !!! Takes 1000ms to return !!!
     */
    fun name(searchFor: String): String {
        val drives = JSensors.get.components().disks
        for (drive in drives) {
            if (drive.name.contains(searchFor)) {
                return drive.name
            }
        }
        return "ERROR"
    }

    fun info() {
        val drives = JSensors.get.components().disks

        if (drives != null) {
            for (drive in drives) {
                this.driveNames.add(drive.name)
            }
        }
        Logger.log("Drives found! (${this.driveNames.joinToString()})", this.javaClass)
    }

}