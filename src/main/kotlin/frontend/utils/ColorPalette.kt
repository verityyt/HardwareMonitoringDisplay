package frontend.utils

import backend.Configuration
import java.awt.Color

object ColorPalette {

    /**
     * CPU color of theme
     */
    val COLOR_CPU = Color.decode(Configuration.get("color_cpu"))

    /**
     * GPU color of theme
     */
    val COLOR_GPU = Color.decode(Configuration.get("color_gpu"))

    /**
     * RAM color of theme
     */
    val COLOR_RAM = Color.decode(Configuration.get("color_ram"))

    /**
     * Background color of theme
     */
    val COLOR_BG = Color.decode(Configuration.get("color_bg"))
}