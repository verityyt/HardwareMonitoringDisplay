package frontend.viewer

import frontend.utils.ColorPalette
import frontend.utils.CustomFont
import java.awt.*

class ReactiveCircles {

    fun paint(
        graphics: Graphics,
        x: Int,
        y: Int,
        diameter: Int,
        color: Color,
        stroke: Float,
        title: String,
        value: String,
        arcCalc: Int
    ) {

        graphics.color = color
        (graphics as Graphics2D).stroke = BasicStroke(stroke)
        graphics.drawArc(x, y, diameter, diameter, 180 - arcCalc, 180 + arcCalc)

        if (CustomFont.light != null) {
            CustomFont.drawCentredString(
                graphics, Rectangle(x, y + 91, diameter, 19), title, ColorPalette.COLOR_1,
                CustomFont.light?.deriveFont(24f)!!
            )
        }

        if (CustomFont.regular != null) {
            CustomFont.drawCentredString(
                graphics, Rectangle(x, y+120, diameter, 33), value, Color.BLACK,
                CustomFont.regular?.deriveFont(45f)!!
            )
        }

    }

}