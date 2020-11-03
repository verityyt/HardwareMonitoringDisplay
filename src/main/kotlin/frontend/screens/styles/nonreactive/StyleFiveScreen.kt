package frontend.screens.styles.nonreactive

import backend.Configuration
import frontend.utils.ColorPalette
import backend.LanguageTranslator
import frontend.Screen
import frontend.viewer.UICircles
import java.awt.*
import java.awt.image.ImageObserver

object StyleFiveScreen : Screen() { // Screen with nonreactive cpu,gpu, drives temperature and ram usage circles

    private var cpuTemperature: String = LanguageTranslator.get("style.loading")
    private var gpuTemperature: String = LanguageTranslator.get("style.loading")
    private var drive1Temperature: String = LanguageTranslator.get("style.loading")
    private var drive2Temperature: String = LanguageTranslator.get("style.loading")
    private var ramLoad: String = LanguageTranslator.get("style.loading")
    private var drive1Name = Configuration.get("drive1_name")
    private var drive2Name = Configuration.get("drive2_name")
    private var drive1Filter = Configuration.get("drive1_filter")
    private var drive2Filter = Configuration.get("drive2_filter")

    init {
        Thread {
            cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
            gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"
            drive1Temperature =
                "${HardwareMonitoringDisplay.systemDrive.temperature(drive1Filter).toString()
                    .split(".")[0]}°C"
            drive2Temperature =
                "${HardwareMonitoringDisplay.systemDrive.temperature(drive2Filter).toString()
                    .split(".")[0]}°C"
            ramLoad = "${HardwareMonitoringDisplay.ram.usedRam()}mb"

            val delay = Configuration.get("update_delay_ms").toLong()

            while (true) {
                cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
                gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"
                drive1Temperature =
                    "${HardwareMonitoringDisplay.systemDrive.temperature(drive1Filter).toString()
                        .split(".")[0]}°C"
                drive2Temperature =
                    "${HardwareMonitoringDisplay.systemDrive.temperature(drive2Filter).toString()
                        .split(".")[0]}°C"
                ramLoad = "${HardwareMonitoringDisplay.ram.usedRam()}mb"

                Thread.sleep(delay)
            }
        }.start()
    }

    override fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver) {
        graphics.color = ColorPalette.COLOR_BG
        graphics.fillRect(0, 0, 800, 600)

        UICircles().paint(
            graphics, 76, 60, 130, ColorPalette.COLOR_CPU, 4f, "CPU", 11f, Rectangle(76, 106, 130, 8),
            cpuTemperature, 26f, Rectangle(76, 120, 130, 20),
            180
        )
        UICircles().paint(
            graphics, 225, 60, 130, ColorPalette.COLOR_RAM, 4f, "RAM", 11f, Rectangle(226, 109, 130, 8),
            ramLoad, 20f, Rectangle(226, 124, 130, 16),
            180
        )
        UICircles().paint(
            graphics, 375, 60, 130, ColorPalette.COLOR_GPU, 4f, "GPU", 11f, Rectangle(375, 108, 130, 8),
            gpuTemperature, 26f, Rectangle(375, 119, 130, 20),
            180
        )
        UICircles().paint(
            graphics, 152, 195, 130, ColorPalette.COLOR_DRIVE1, 4f, drive1Name, 11f, Rectangle(153, 240, 130, 8),
            drive1Temperature, 26f, Rectangle(153, 256, 130, 19),
            180
        )
        UICircles().paint(
            graphics, 300, 195, 130, ColorPalette.COLOR_DRIVE2, 4f, drive2Name, 11f, Rectangle(301, 240, 130, 8),
            drive2Temperature, 26f, Rectangle(301, 256, 130, 20),
            180
        )

    }

}