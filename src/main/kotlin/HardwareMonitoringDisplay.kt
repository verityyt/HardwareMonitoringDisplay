import backend.monitoring.CPU
import backend.monitoring.GPU

object HardwareMonitoringDisplay {

    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting Hardware Monitoring Display...")

        val cpu = CPU()
        val gpu = GPU()
        println("CPU found! (${cpu.name} | ${cpu.temperature().toString().split(".")[0]}°C)")
        println("GPU found! (${gpu.name} | ${gpu.temperature().toString().split(".")[0]}°C)")

        println("Hardware Monitoring Display started!")
    }

}