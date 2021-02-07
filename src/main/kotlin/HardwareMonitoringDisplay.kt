import backend.Configuration
import backend.Logger
import backend.NotificationManager
import backend.monitoring.CPU
import backend.monitoring.DRIVE
import backend.monitoring.GPU
import backend.monitoring.RAM
import frontend.WindowHandler
import frontend.screens.StartingScreen
import frontend.utils.CustomFont
import java.util.*
import kotlin.system.exitProcess

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
     * The System-DRIVE of the current PC
     */
    lateinit var systemDrive: DRIVE

    /**
     * The DRIVE of the current PC
     */
    lateinit var drive: DRIVE

    /**
     * Version of the Application
     */
    val version = "v0.2.2"

    val ohmProcess = ProcessBuilder("files\\ohm_0-9-6\\OpenHardwareMonitor.exe").start()

    @JvmStatic
    fun main(args: Array<String>) {
        Logger.log("Starting Hardware Monitoring Display...", this.javaClass)

        Configuration.check()
        WindowHandler.openWindow()
        NotificationManager.startUp()

        Timer().schedule(object : TimerTask() {
            override fun run() {

                if (WindowHandler.screen is StartingScreen) {
                    (WindowHandler.screen as StartingScreen).animateLoading(20, 30)
                }


                Logger.log("Checking OS...", this.javaClass)

                if (WindowHandler.screen is StartingScreen) {
                    (WindowHandler.screen as StartingScreen).startingText = "loading.os"
                    (WindowHandler.screen as StartingScreen).animateLoading(25, 35)
                }

                val os = System.getProperty("os.name")
                if (os != "Windows 10") {
                    Logger.log("Wrong operating system! ($os)", this.javaClass)
                    exitProcess(0)
                }

                Logger.log("Registering fonts...", this.javaClass)

                CustomFont.registerFonts().also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.cpu"
                        (WindowHandler.screen as StartingScreen).animateLoading(45, 30)
                    }
                }

                cpu = CPU
                gpu = GPU
                ram = RAM
                systemDrive = DRIVE
                drive = DRIVE

                Logger.log("RAM found! (${ram.maxRam()}mb)", this.javaClass)

                Logger.log("Searching for cpu...", this.javaClass)

                Logger.log("CPU found! (${cpu.name()})", this.javaClass).also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.gpu"
                        (WindowHandler.screen as StartingScreen).animateLoading(60, 30)
                    }
                }

                Logger.log("Searching for gpu...", this.javaClass)

                Logger.log("GPU found! (${gpu.name()})", this.javaClass).also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.drives"
                        (WindowHandler.screen as StartingScreen).animateLoading(75, 30)
                    }
                }

                Logger.log("Searching for drives...", this.javaClass)

                systemDrive.info().also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.finished"
                        (WindowHandler.screen as StartingScreen).animateLoading(100, 30)
                    }
                }

                Logger.log("Hardware Monitoring Display started!", this.javaClass)
            }
        }, 1000)

    }

}