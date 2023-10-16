package com.stochastictinkr.skywing.components.borders

import java.awt.Color
import javax.swing.border.LineBorder

fun line(color: Color? = null, thickness: Int = 1, roundedCorners: Boolean) =
    LineBorder(color, thickness, roundedCorners)