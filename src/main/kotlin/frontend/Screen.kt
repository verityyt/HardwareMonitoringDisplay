package frontend

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver
import javax.print.DocFlavor

abstract class Screen {

    abstract var cpuTemperature: String?
    abstract var gpuTemperature: String?
    abstract var drive1Temperature: String?
    abstract var drive2Temperature: String?
    abstract var ramLoad: String?

    abstract fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver)

}