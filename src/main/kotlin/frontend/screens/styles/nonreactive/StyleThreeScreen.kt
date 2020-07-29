package frontend.screens.styles.nonreactive

import backend.Configuration
import frontend.utils.ColorPalette
import frontend.LanguageTranslator
import frontend.Screen
import frontend.viewer.UICircles
import java.awt.*
import java.awt.image.ImageObserver

object StyleThreeScreen : Screen() { // Screen with nonreactive cpu,gpu temperature and ram usage circles

    private var cpuTemperature: String = LanguageTranslator.get("style.loading")
    private var gpuTemperature: String = LanguageTranslator.get("style.loading")
    private var ramLoad: String = LanguageTranslator.get("style.loading")

    init {
        Thread {
            cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
            gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"
            ramLoad = "${HardwareMonitoringDisplay.ram.usedRam()}mb"

            val delay = Configuration.get("update_delay_ms").toLong()

            while (true) {
                cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
                gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"
                ramLoad = "${HardwareMonitoringDisplay.ram.usedRam()}mb"

                Thread.sleep(delay)
            }
        }.start()
    }

    override fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver) {
        graphics.color = ColorPalette.COLOR_BG
        graphics.fillRect(0, 0, 800, 600)

        UICircles().paint(
            graphics, 70, 200, 200, ColorPalette.COLOR_CPU, 5f, "CPU", 18f, Rectangle(70, 274, 200, 15),
            cpuTemperature, 34f, Rectangle(70, 297, 200, 25),
            180
        )
        UICircles().paint(
            graphics, 300, 200, 200, ColorPalette.COLOR_RAM, 5f, "RAM", 18f, Rectangle(300, 274, 200, 15),
            ramLoad, 34f, Rectangle(300, 297, 200, 25),
            180
        )
        UICircles().paint(
            graphics, 530, 200, 200, ColorPalette.COLOR_GPU, 5f, "GPU", 18f, Rectangle(530, 274, 200, 15),
            gpuTemperature, 34f, Rectangle(530, 297, 200, 25),
            180
        )

    }

}