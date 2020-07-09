package backend.monitoring

import java.lang.management.ManagementFactory
import com.sun.management.OperatingSystemMXBean

object RAM {

    /**
     * Returns the size of the ram of the pc
     * !!! Takes 1000ms to return !!!
     */
    fun maxRam() : Int {

        val memorySize =
            (ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean).totalPhysicalMemorySize
        val gbytes = (memorySize / (1024 * 1024))

        return gbytes.toInt()
    }

    /**
     * Returns the size of the free ram of the pc
     * !!! Takes 1000ms to return !!!
     */
    fun usedRam() : Int {

        val memorySizeFree =
            (ManagementFactory.getOperatingSystemMXBean() as OperatingSystemMXBean).freePhysicalMemorySize
        val gbytesFree = (memorySizeFree / (1024 * 1024))

        val gbytes = maxRam() - gbytesFree

        return gbytes.toInt()
    }

}