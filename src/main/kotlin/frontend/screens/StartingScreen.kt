package frontend.screens

import frontend.CustomFont
import frontend.LanguageTranslator
import frontend.Screen
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.ImageObserver
import javax.imageio.ImageIO
import kotlin.isInitialized as isInitialized1

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

        graphics.drawImage(ImageIO.read(javaClass.getResource("/images/Rocket.png")),337,138,125,125, observer)

        graphics.color = Color.BLACK
        graphics.fillRect(149, 306, 502, 8)

        graphics.color = Color.WHITE
        graphics.fillRect(150, 307, 500, 6)

        graphics.color = Color.BLACK
        graphics.fillRect(150, 307, startingPercentage * 5, 6)

        if (CustomFont.regular == null) {
            CustomFont.registerRegular()
        }
        CustomFont.drawCentredString(
            graphics,
            Rectangle(0, 327, 800, 16),
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
                // Finished start
                Thread.currentThread().stop()
            }
        }
    }

}

