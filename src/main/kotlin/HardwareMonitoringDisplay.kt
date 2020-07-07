import backend.monitoring.CPU
import backend.monitoring.GPU

object HardwareMonitoringDisplay {

    lateinit var cpu: CPU
    lateinit var gpu: GPU

    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting Hardware Monitoring Display...")

        cpu = CPU
        gpu = GPU

        println("CPU found! (${cpu.name()} | ${cpu.temperature().toString().split(".")[0]}°C)")
        println("GPU found! (${gpu.name()} | ${gpu.temperature().toString().split(".")[0]}°C)")

        println("Hardware Monitoring Display started!")
    }

}