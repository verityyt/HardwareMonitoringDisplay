package frontend.screens.styles.reactive

import backend.Configuration
import frontend.utils.ColorPalette
import frontend.LanguageTranslator
import frontend.Screen
import frontend.viewer.UICircles
import java.awt.*
import java.awt.image.ImageObserver

object SytleZeroScreen : Screen() { // Screen with reactive cpu and gpu temperature circles

    private var cpuTemperature: String = LanguageTranslator.get("style.loading")
    private var gpuTemperature: String = LanguageTranslator.get("style.loading")
    private var cpuArcCalc: Int = 0
    private var gpuArcCalc: Int = 0

    init {
        Thread {
            cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
            cpuArcCalc = ((cpuTemperature.replace("°C", "").toInt()) * 1.8).toInt()
            gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"
            gpuArcCalc = ((gpuTemperature.replace("°C", "").toInt()) * 1.8).toInt()

            val delay = Configuration.get("update_delay_ms").toLong()

            while (true) {
                cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
                cpuArcCalc = ((cpuTemperature.replace("°C", "").toInt()) * 1.8).toInt()
                gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"
                gpuArcCalc = ((gpuTemperature.replace("°C", "").toInt()) * 1.8).toInt()

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
            cpuArcCalc
        )
        UICircles().paint(
            graphics, 460, 175, 250, ColorPalette.COLOR_GPU, 7f, "GPU", 24f, Rectangle(460, 175 + 91, 250, 19),
            gpuTemperature, 45f, Rectangle(460, 180 + 120, 250, 19),
            gpuArcCalc
        )

    }

}