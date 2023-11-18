package com.stochastictinkr.skywing.components.borders

import java.awt.Color
import javax.swing.border.BevelBorder

/**
 * Create a bevel border. If the colors are not specified, they will be derived from the background color of the
 * component.
 */
fun bevel(
    type: BevelType = BevelType.RAISED,
    highlightOuterColor: Color? = null,
    highlightInnerColor: Color? = null,
    shadowOuterColor: Color? = null,
    shadowInnerColor: Color? = null,
): BevelBorder =
    BevelBorder(type.value, highlightOuterColor, highlightInnerColor, shadowOuterColor, shadowInnerColor)

/**
 * Create a bevel border.
 */
fun bevel(
    type: BevelType = BevelType.RAISED,
    highlight: Color,
    shadow: Color,
): BevelBorder = BevelBorder(type.value, highlight.brighter(), highlight, shadow, shadow.brighter())

