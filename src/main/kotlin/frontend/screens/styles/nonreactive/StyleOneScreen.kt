package frontend.screens.styles.nonreactive

import backend.Configuration
import frontend.utils.ColorPalette
import backend.LanguageTranslator
import frontend.Screen
import frontend.viewer.UICircles
import java.awt.*
import java.awt.image.ImageObserver

object StyleOneScreen : Screen() { // Screen with nonreactive cpu and gpu temperature circles

    private var cpuTemperature: String = LanguageTranslator.get("style.loading")
    private var gpuTemperature: String = LanguageTranslator.get("style.loading")

    init {
        Thread {
            cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
            gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"

            val delay = Configuration.get("update_delay_ms").toLong()

            while (true) {
                cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
                gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"

                Thread.sleep(delay)
            }
        }.start()
    }

    override fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver) {
        graphics.color = ColorPalette.COLOR_BG
        graphics.fillRect(0, 0, 800, 600)

        UICircles().paint(
            graphics, 90, 175, 250, ColorPalette.COLOR_CPU, 7f, "CPU", 24f, Rectangle(90, 175 + 91, 250, 19),
            cpuTemperature, 45f, Rectangle(90, 180 + 120, 250, 19),
            180
        )
        UICircles().paint(
            graphics, 460, 175, 250, ColorPalette.COLOR_GPU, 7f, "GPU", 24f, Rectangle(460, 175 + 91, 250, 19),
            gpuTemperature, 45f, Rectangle(460, 180 + 120, 250, 19),
            180
        )

    }

}