package com.stochastictinkr.skywing.components.borders

import java.awt.Color
import javax.swing.border.BevelBorder

fun bevel(
    type: BevelType = BevelType.RAISED,
    highlightOuterColor: Color? = null,
    highlightInnerColor: Color? = null,
    shadowOuterColor: Color? = null,
    shadowInnerColor: Color? = null,
): BevelBorder =
    BevelBorder(type.value, highlightOuterColor, highlightInnerColor, shadowOuterColor, shadowInnerColor)

fun bevel(
    type: BevelType = BevelType.RAISED,
    highlight: Color,
    shadow: Color,
): BevelBorder = BevelBorder(type.value, highlight.brighter(), highlight, shadow, shadow.brighter())

