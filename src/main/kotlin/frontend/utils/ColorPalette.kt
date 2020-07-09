package frontend.utils

import backend.Configuration
import java.awt.Color

object ColorPalette {

    /**
     * First accent color of theme
     */
    val FIRST_ACCENT = Color.decode(Configuration.get("accent_color_1"))

    /**
     * Second accent color of theme
     */
    val SECOND_ACCENT = Color.decode(Configuration.get("accent_color_2"))

}