package frontend

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver

abstract class Screen {

    abstract fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver)

}