import backend.Configuration
import backend.monitoring.CPU
import backend.monitoring.GPU
import backend.monitoring.RAM
import frontend.WindowHandler
import frontend.screens.StartingScreen
import frontend.utils.CustomFont
import java.util.*

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
     * The RAM of the current PC
     */
    lateinit var ram: RAM

    /**
     * Version of the Application
     */
    val version = "v0.1.4"

    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting Hardware Monitoring Display...")

        WindowHandler.openWindow()

        Timer().schedule(object : TimerTask() {
            override fun run() {

                if (WindowHandler.screen is StartingScreen) {
                    (WindowHandler.screen as StartingScreen).animateLoading( 20, 30)
                }

                println("Creating/Loading configuration")

                Configuration.create().also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.config"
                        (WindowHandler.screen as StartingScreen).animateLoading( 35, 30)
                    }
                }

                println("Registering fonts...")

                CustomFont.registerFonts().also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.cpu"
                        (WindowHandler.screen as StartingScreen).animateLoading( 45, 30)
                    }
                }

                cpu = CPU
                gpu = GPU
                ram = RAM

                println("RAM found! (${ram.maxRam()}mb)")

                println("Searching for cpu...")

                println("CPU found! (${cpu.name()})").also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.gpu"
                        (WindowHandler.screen as StartingScreen).animateLoading( 79, 30)
                    }
                }

                println("Searching for gpu...")

                println("GPU found! (${gpu.name()})").also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.finished"
                        (WindowHandler.screen as StartingScreen).animateLoading( 100, 30)
                    }
                }

                println("Hardware Monitoring Display started!")
            }
        },1000)

    }

}