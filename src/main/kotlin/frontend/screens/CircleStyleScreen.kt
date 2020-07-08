package frontend.screens

import backend.Configuration
import frontend.ColorPalette
import frontend.CustomFont
import frontend.LanguageTranslator
import frontend.Screen
import java.awt.*
import java.awt.image.ImageObserver

object CircleStyleScreen : Screen() {

    private var cpuTemperature: String = LanguageTranslator.get("style.loading")
    private var gpuTemperature: String = LanguageTranslator.get("style.loading")
    private var cpuArcCalc: Int = 0
    private var gpuArcCalc: Int = 0

    init {
        Thread {
            cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
            cpuArcCalc = ((cpuTemperature.replace("°C","").toInt()) * 1.8).toInt()
            gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"
            gpuArcCalc = ((gpuTemperature.replace("°C","").toInt()) * 1.8).toInt()

            val delay = Configuration.get("update_delay_ms").toLong()

            while (true) {
                cpuTemperature = "${HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0]}°C"
                cpuArcCalc = ((cpuTemperature.replace("°C","").toInt()) * 1.8).toInt()
                gpuTemperature = "${HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]}°C"
                gpuArcCalc = ((gpuTemperature.replace("°C","").toInt()) * 1.8).toInt()

                Thread.sleep(delay)
            }
        }.start()
    }

    override fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver) {

        // Draw Blue Circle
        graphics.color = ColorPalette.BLUE
        graphics2D.stroke = BasicStroke(7f)
        graphics.drawArc(90,115,250,250,180 - cpuArcCalc,180 + cpuArcCalc)

        // Draw Purple Circle
        graphics.color = ColorPalette.PURPLE
        graphics2D.stroke = BasicStroke(7f)
        graphics.drawArc(460,115,250,250,180 - gpuArcCalc,180 + gpuArcCalc)

        if (CustomFont.light != null) {
            CustomFont.drawCentredString(
                graphics, Rectangle(90, 206, 250, 19), "CPU", ColorPalette.BLUE,
                CustomFont.light?.deriveFont(24f)!!
            )

            CustomFont.drawCentredString(
                graphics, Rectangle(460, 206, 250, 19), "GPU", ColorPalette.PURPLE,
                CustomFont.light?.deriveFont(24f)!!
            )
        }

        if (CustomFont.regular != null) {

            CustomFont.drawCentredString(
                graphics, Rectangle(90, 235, 250, 33), cpuTemperature, Color.BLACK,
                CustomFont.regular?.deriveFont(45f)!!
            )

            CustomFont.drawCentredString(
                graphics, Rectangle(460, 235, 250, 33), gpuTemperature, Color.BLACK,
                CustomFont.regular?.deriveFont(45f)!!
            )
        }

    }

}