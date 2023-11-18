package com.stochastictinkr.skywing.components.borders

import java.awt.Color
import javax.swing.border.SoftBevelBorder

/**
 * Create a soft  bevel border. If the colors are not specified, they will be derived from the background color of the
 * component.
 */
fun softBevel(
    type: BevelType = BevelType.RAISED,
    highlightOuterColor: Color? = null,
    highlightInnerColor: Color? = null,
    shadowOuterColor: Color? = null,
    shadowInnerColor: Color? = null,
): SoftBevelBorder =
    SoftBevelBorder(type.value, highlightOuterColor, highlightInnerColor, shadowOuterColor, shadowInnerColor)

/**
 * Create a soft bevel border.
 */
fun softBevel(
    type: BevelType = BevelType.RAISED,
    highlight: Color,
    shadow: Color,
): SoftBevelBorder =
    SoftBevelBorder(type.value, highlight.brighter(), highlight, shadow, shadow.brighter())