package com.stochastictinkr.skywing.components.borders

import java.awt.Color
import javax.swing.border.LineBorder

/**
 * Creates a line border.
 */
fun line(color: Color? = null, thickness: Int = 1, roundedCorners: Boolean) =
    LineBorder(color, thickness, roundedCorners)