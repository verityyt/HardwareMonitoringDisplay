package frontend

import frontend.screens.StartingScreen
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import javax.imageio.ImageIO
import javax.swing.JComponent
import javax.swing.JFrame
import javax.swing.WindowConstants

object WindowHandler {

    /**
     * Window of the complete frontend
     */
    private lateinit var window: JFrame

    /**
     * Component of the window
     */
    private lateinit var component: JComponent

    /**
     * Current screen of the window
     */
    lateinit var screen: Screen

    fun openWindow() {
        Thread {

            screen = StartingScreen

            component = object : JComponent() {
                override fun paint(graphics: Graphics?) {
                    if (graphics != null) {
                        (graphics as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        (graphics as Graphics2D).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                        graphics.color = Color.WHITE
                        graphics.fillRect(0, 0, 800, 480)

                        screen.paint(graphics, graphics as Graphics2D, this)
                    }
                }
            }

            window = JFrame()
            window.add(component)

            window.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

            window.isUndecorated = false
            window.setSize(800, 480)
            window.isResizable = false
            window.isAlwaysOnTop = true
            window.title = "Hardware Monitoring Display | ${HardwareMonitoringDisplay.version}"
            window.iconImage = ImageIO.read(javaClass.getResource("/images/WindowIcon.png"))

            window.isVisible = true

            while (true) {
                Thread.sleep(1000 / 60)
                window.repaint()
            }
        }.start()
    }

}