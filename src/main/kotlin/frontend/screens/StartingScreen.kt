package frontend.screens

import backend.Configuration
import frontend.utils.CustomFont
import frontend.LanguageTranslator
import frontend.Screen
import frontend.WindowHandler
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.ImageObserver
import javax.imageio.ImageIO

object StartingScreen : Screen() {

    /**
     * Current percentage of the start process
     */
    private var startingPercentage = 0

    /**
     * Current process of start process
     */
    var startingText = "loading.fonts"

    override fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver) {

        graphics.drawImage(ImageIO.read(javaClass.getResource("/images/Rocket.png")),337,198,125,125, observer)

        graphics.color = Color.BLACK
        graphics.fillRect(149, 366, 502, 8)

        graphics.color = Color.WHITE
        graphics.fillRect(150, 367, 500, 6)

        graphics.color = Color.BLACK
        graphics.fillRect(150, 367, startingPercentage * 5, 6)

        if (CustomFont.regular == null) {
            CustomFont.registerRegular()
        }
        CustomFont.drawCentredString(
            graphics,
            Rectangle(0, 387, 800, 16),
            LanguageTranslator.get(startingText),
            Color.BLACK,
            CustomFont.regular?.deriveFont(16f)!!
        )

    }

    fun animateLoading(target: Int, sleep: Long) {
        for (percent in startingPercentage..target) {
            Thread.sleep(sleep)
            startingPercentage = percent
            if (percent == 100) {
                val style = Configuration.get("style").toInt()

                if(style == 0) {
                    WindowHandler.screen = CircleStyleScreen
                }else {
                    println("No style which number $style found!")
                }

                Thread.currentThread().stop()
            }
        }
    }

}

