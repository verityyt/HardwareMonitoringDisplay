package frontend.utils

import backend.Configuration
import java.awt.Color

object ColorPalette {

    /**
     * First color of theme
     */
    val COLOR_1 = Color.decode(Configuration.get("color_1"))

    /**
     * Second color of theme
     */
    val COLOR_2 = Color.decode(Configuration.get("color_2"))


    /**
     * Background color of theme
     */
    val COLOR_BG = Color.decode(Configuration.get("color_bg"))
}