package frontend

import java.awt.Graphics
import java.awt.Graphics2D
import java.util.*
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

        // screen = Put the first screen here

        component = object : JComponent() {
            override fun paint(graphics: Graphics?) {
                if (graphics != null) {
                    screen.paint(graphics ,graphics as Graphics2D)
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

        window.isVisible = true

        while(true) {
            Thread.sleep(1000/30)
            window.repaint()
        }

    }

}