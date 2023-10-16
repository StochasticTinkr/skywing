package com.stochastictinkr.skywing.components.borders

import java.awt.Color
import javax.swing.border.SoftBevelBorder

fun softBevel(
    type: BevelType = BevelType.RAISED,
    highlightOuterColor: Color? = null,
    highlightInnerColor: Color? = null,
    shadowOuterColor: Color? = null,
    shadowInnerColor: Color? = null,
): SoftBevelBorder =
    SoftBevelBorder(type.value, highlightOuterColor, highlightInnerColor, shadowOuterColor, shadowInnerColor)

fun softBevel(
    type: BevelType = BevelType.RAISED,
    highlight: Color,
    shadow: Color,
): SoftBevelBorder =
    SoftBevelBorder(type.value, highlight.brighter(), highlight, shadow, shadow.brighter())