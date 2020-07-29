package frontend

import frontend.screens.StartingScreen
import frontend.utils.ColorPalette
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.RenderingHints
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.io.File
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

    /**
     * Changeable fps count of the window
     */
    var fps: Long = 60

    fun openWindow() {
        Thread {

            screen = StartingScreen

            component = object : JComponent() {
                override fun paint(graphics: Graphics?) {
                        if (graphics != null) {
                            (graphics as Graphics2D).setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
                            (graphics as Graphics2D).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
                            (graphics as Graphics2D).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON)
                            (graphics as Graphics2D).setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)
                            (graphics as Graphics2D).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
                            (graphics as Graphics2D).setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON)

                            graphics.color = Color.WHITE
                            graphics.fillRect(0, 0, 800, 600)

                            screen.paint(graphics, graphics, this)
                        }
                }
            }

            window = JFrame()
            window.add(component)

            window.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE

            window.addKeyListener(object : KeyListener {
                override fun keyTyped(e: KeyEvent?) {
                }

                override fun keyPressed(e: KeyEvent?) {
                }

                override fun keyReleased(e: KeyEvent?) {
                    if (e?.keyCode == 122) {

                        window.dispose()
                        if (window.isUndecorated) {
                            window.isUndecorated = false
                            window.isAlwaysOnTop = false
                            println("Switched to windowed mode")
                        } else {
                            window.isUndecorated = true
                            window.isAlwaysOnTop = true
                            println("Switched to fullscreen mode")
                        }
                        window.isVisible = true
                    }
                }

            })

            window.isUndecorated = false
            window.setSize(800, 600)
            window.isResizable = false
            window.isAlwaysOnTop = false
            window.title = "Hardware Monitoring Display | ${HardwareMonitoringDisplay.version} (800x600)"
            window.iconImage = ImageIO.read(File("resources/images/WindowIcon.png"))

            window.isVisible = true

            while (true) {
                Thread.sleep(1000 / fps)
                window.repaint()
            }
        }.start()
    }

}