package frontend.screens

import backend.Configuration
import frontend.ColorPalette
import frontend.CustomFont
import frontend.LanguageTranslator
import frontend.Screen
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.ImageObserver

object CircleStyleScreen : Screen() {

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

        // Draw Blue Circle
        graphics.color = ColorPalette.BLUE
        graphics.fillOval(90, 115, 250, 250)

        graphics.color = Color.WHITE
        graphics.fillOval(97, 123, 235, 235)

        // Draw Purple Circle
        graphics.color = ColorPalette.PURPLE
        graphics.fillOval(460, 115, 250, 250)

        graphics.color = Color.WHITE
        graphics.fillOval(467, 123, 235, 235)

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
                graphics, Rectangle(90, 235, 250, 33), "$cpuTemperature", Color.BLACK,
                CustomFont.regular?.deriveFont(45f)!!
            )

            CustomFont.drawCentredString(
                graphics, Rectangle(460, 235, 250, 33), "$gpuTemperature", Color.BLACK,
                CustomFont.regular?.deriveFont(45f)!!
            )
        }

    }

}