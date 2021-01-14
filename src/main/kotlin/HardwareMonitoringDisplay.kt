import backend.Configuration
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
    val version = "v0.2.1"

    val ohmProcess = ProcessBuilder("files\\ohm_0-9-6\\OpenHardwareMonitor.exe").start()

    @JvmStatic
    fun main(args: Array<String>) {
        println("Starting Hardware Monitoring Display...")

        WindowHandler.openWindow()
        NotificationManager.startUp()

        Timer().schedule(object : TimerTask() {
            override fun run() {

                if (WindowHandler.screen is StartingScreen) {
                    (WindowHandler.screen as StartingScreen).animateLoading(20, 30)
                }

                println("Checking OS...")

                if (WindowHandler.screen is StartingScreen) {
                    (WindowHandler.screen as StartingScreen).startingText = "loading.os"
                    (WindowHandler.screen as StartingScreen).animateLoading(25, 30)
                }

                val os = System.getProperty("os.name")
                if(os != "Windows 10") {
                    println("Wrong operating system! ($os)")
                    exitProcess(0)
                }

                println("Creating/Loading configuration...")

                Configuration.create().also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.config"
                        (WindowHandler.screen as StartingScreen).animateLoading(35, 30)
                    }
                }

                println("Registering fonts...")

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

                println("RAM found! (${ram.maxRam()}mb)")

                println("Searching for cpu...")

                println("CPU found! (${cpu.name()})").also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.gpu"
                        (WindowHandler.screen as StartingScreen).animateLoading(60, 30)
                    }
                }

                println("Searching for gpu...")

                println("GPU found! (${gpu.name()})").also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.drives"
                        (WindowHandler.screen as StartingScreen).animateLoading(75, 30)
                    }
                }

                println("Searching for drives...")

                systemDrive.info().also {
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "loading.finished"
                        (WindowHandler.screen as StartingScreen).animateLoading(100, 30)
                    }
                }

                println("Hardware Monitoring Display started!")
            }
        }, 1000)

    }

}