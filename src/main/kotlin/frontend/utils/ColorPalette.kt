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
     * SSD color of theme
     */
    val COLOR_SSD = Color.decode(Configuration.get("color_ssd"))

    /**
     * HDD color of theme
     */
    val COLOR_HDD = Color.decode(Configuration.get("color_hdd"))

    /**
     * Text color of theme
     */
    val COLOR_TEXT = Color.decode(Configuration.get("color_text"))

    /**
     * Background color of theme
     */
    val COLOR_BG = Color.decode(Configuration.get("color_bg"))
}