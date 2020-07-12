package frontend.viewer

import frontend.utils.ColorPalette
import frontend.utils.CustomFont
import java.awt.*

class UICircles {

    fun paint(
        graphics: Graphics,
        x: Int,
        y: Int,
        diameter: Int,
        color: Color,
        stroke: Float,
        title: String,
        titleSize: Float,
        titleRect: Rectangle,
        value: String,
        valueSize: Float,
        valueRect: Rectangle,
        arcCalc: Int
    ) {

        graphics.color = color
        (graphics as Graphics2D).stroke = BasicStroke(stroke)
        graphics.drawArc(x, y, diameter, diameter, 180 - arcCalc, 180 + arcCalc)

        if (CustomFont.light != null) {
            CustomFont.drawCentredString(
                graphics, titleRect, title, color,
                CustomFont.light?.deriveFont(titleSize)!!
            )
        }

        if (CustomFont.regular != null) {
            CustomFont.drawCentredString(
                graphics, valueRect, value, ColorPalette.COLOR_TEXT,
                CustomFont.regular?.deriveFont(valueSize)!!
            )
        }

    }

}