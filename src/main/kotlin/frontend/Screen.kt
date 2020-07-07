package frontend

import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JComponent

abstract class Screen(index: Int) {

    abstract fun paint(graphics: Graphics, graphics2D: Graphics2D)

}