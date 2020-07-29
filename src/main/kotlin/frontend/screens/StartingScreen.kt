package frontend.screens

import backend.Configuration
import frontend.utils.CustomFont
import frontend.LanguageTranslator
import frontend.Screen
import frontend.WindowHandler
import frontend.screens.styles.nonreactive.StyleOneScreen
import frontend.screens.styles.reactive.StyleTwoScreen
import frontend.screens.styles.reactive.StyleZeroScreen
import frontend.screens.styles.nonreactive.StyleThreeScreen
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.image.ImageObserver
import java.io.File
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

        graphics.drawImage(ImageIO.read(File("resources/images/Rocket.png")),337,198,125,125, observer)

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

                WindowHandler.fps = 24

                if(style == 0) {
                    WindowHandler.screen =
                        StyleZeroScreen
                }else if(style == 1) {
                    WindowHandler.screen =
                        StyleOneScreen
                }else if(style == 2) {
                    WindowHandler.screen =
                        StyleTwoScreen
                }else if(style == 3) {
                    WindowHandler.screen =
                        StyleThreeScreen
                }else {
                    println("No style with number $style found!")
                    if (WindowHandler.screen is StartingScreen) {
                        (WindowHandler.screen as StartingScreen).startingText = "style.unknown.$style"
                        (WindowHandler.screen as StartingScreen).animateLoading( 79, 30)
                    }
                }

                Thread.currentThread().stop()
            }
        }
    }

}

