import backend.monitoring.CPU
import backend.monitoring.GPU
import frontend.WindowHandler

object HardwareMonitoringDisplay {

    /**
     * The CPU of the current PC
     */
    lateinit var cpu: CPU

    /**
     * The GPU of the current PC
     */
    lateinit var gpu: GPU

    /**
     * Version of the Application
     */
    val version = "v0.0.2"

    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting Hardware Monitoring Display...")

        cpu = CPU
        gpu = GPU

        println("CPU found! (${cpu.name()} | ${cpu.temperature().toString().split(".")[0]}°C)")
        println("GPU found! (${gpu.name()} | ${gpu.temperature().toString().split(".")[0]}°C)")

        WindowHandler.openWindow()

        println("Hardware Monitoring Display started!")
    }

}