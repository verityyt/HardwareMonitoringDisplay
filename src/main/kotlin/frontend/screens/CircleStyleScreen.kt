package frontend.screens

import frontend.ColorPalette
import frontend.CustomFont
import frontend.Screen
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.ImageObserver

object CircleStyleScreen : Screen() {

    override fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver) {

        // Draw Blue Circle
        graphics.color = ColorPalette.BLUE
        graphics.fillOval(90, 115-20, 250, 250)

        graphics.color = Color.WHITE
        graphics.fillOval(97, 123-20, 235, 235)

        // Draw Purple Circle
        graphics.color = ColorPalette.PURPLE
        graphics.fillOval(460, 115-20, 250, 250)

        graphics.color = Color.WHITE
        graphics.fillOval(467, 123-20, 235, 235)

        if (CustomFont.light != null) {
            CustomFont.drawCentredString(graphics, Rectangle(90, 206-20, 250, 19), "CPU", ColorPalette.BLUE,
                CustomFont.light?.deriveFont(24f)!!
            )

            CustomFont.drawCentredString(graphics, Rectangle(460, 206-20, 250, 19), "GPU", ColorPalette.PURPLE,
                CustomFont.light?.deriveFont(24f)!!
            )
        }

        if (CustomFont.regular != null) {
            val cpuTemperature = HardwareMonitoringDisplay.cpu.temperature().toString().split(".")[0].toInt()
            CustomFont.drawCentredString(graphics, Rectangle(90, 235-20, 250, 33), "$cpuTemperature°C", Color.BLACK,
                CustomFont.regular?.deriveFont(45f)!!
            )



            val gpuTemperature = HardwareMonitoringDisplay.gpu.temperature().toString().split(".")[0]
            CustomFont.drawCentredString(graphics, Rectangle(460, 235-20, 250, 33), "$gpuTemperature°C", Color.BLACK,
                CustomFont.regular?.deriveFont(45f)!!
            )
        }

    }

}