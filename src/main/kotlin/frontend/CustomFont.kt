package frontend

import java.awt.*
import java.io.File
import javax.swing.Spring.width


object CustomFont {

    var regular: Font? = null

    fun registerFonts() {
        registerRegular()

        println("Registered Fonts!")
    }

    fun registerRegular() {
        regular =
            Font.createFont(Font.TRUETYPE_FONT, File(javaClass.getResource("/fonts/Product-Sans-Regular.ttf").toURI()))
        val ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
        ge.registerFont(regular)
    }

    fun drawCentredString(graphics: Graphics, rect: Rectangle, text: String, color: Color, font: Font) {
        val metrics: FontMetrics = graphics.getFontMetrics(font)
        val x: Int = rect.x + (rect.width - metrics.stringWidth(text)) / 2
        val y: Int = rect.y + (rect.height - metrics.height) / 2 + metrics.ascent
        graphics.font = font
        graphics.color = color
        graphics.drawString(text, x, y)
    }

}