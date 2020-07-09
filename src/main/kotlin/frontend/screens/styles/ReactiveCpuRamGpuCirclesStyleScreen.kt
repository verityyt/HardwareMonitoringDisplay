package frontend.screens.styles

import backend.Configuration
import frontend.utils.ColorPalette
import frontend.LanguageTranslator
import frontend.Screen
import frontend.viewer.UICircles
import java.awt.*
import java.awt.image.ImageObserver

object ReactiveCpuRamGpuCirclesStyleScreen : Screen() {

    private var cpuTemperature: String = LanguageTranslator.get("style.loading")
    private var gpuTemperature: String = LanguageTranslator.get("style.loading")
    private var ramLoad: String = LanguageTranslator.get("style.loading")
    private var cpuArcCalc: Int = 0
    private var gpuArcCalc: Int = 0
    private var ramArcCalc: Int = 0

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
            graphics, 70, 200, 200, ColorPalette.COLOR_1, 5f, "CPU", 18f, Rectangle(70, 274, 200, 15),
            cpuTemperature, 34f, Rectangle(70, 297, 200, 25),
            cpuArcCalc
        )
        UICircles().paint(
            graphics, 300, 200, 200, ColorPalette.COLOR_3, 5f, "RAM", 18f, Rectangle(300, 274, 200, 15),
            ramLoad, 34f, Rectangle(300, 297, 200, 25),
            ramArcCalc
        )
        UICircles().paint(
            graphics, 530, 200, 200, ColorPalette.COLOR_2, 5f, "GPU", 18f, Rectangle(530, 274, 200, 15),
            gpuTemperature, 34f, Rectangle(530, 297, 200, 25),
            gpuArcCalc
        )

    }

}