package frontend

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.ImageObserver
import java.io.File
import javax.imageio.ImageIO

object StartingScreen : Screen() {

    override fun paint(graphics: Graphics, graphics2D: Graphics2D, observer: ImageObserver) {
        graphics.drawImage(ImageIO.read(javaClass.getResource("/Rocket.png")),200,20,400,410,observer)
    }

}

