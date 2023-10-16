package com.stochastictinkr.skywing.components.borders

import java.awt.Color
import java.awt.Font
import javax.swing.border.Border
import javax.swing.border.TitledBorder

fun titled(
    title: String? = null,
    border: Border? = null,
    justification: TitleJustification = TitleJustification.DEFAULT,
    titlePosition: TitlePosition = TitlePosition.DEFAULT,
    font: Font? = null,
    color: Color? = null,
) = TitledBorder(border, title, justification.justification, titlePosition.position, font, color)

enum class TitlePosition(val position: Int) {
    DEFAULT(TitledBorder.DEFAULT_POSITION),
    ABOVE_TOP(TitledBorder.ABOVE_TOP),
    TOP(TitledBorder.TOP),
    BELOW_TOP(TitledBorder.BELOW_TOP),
    ABOVE_BOTTOM(TitledBorder.ABOVE_BOTTOM),
    BOTTOM(TitledBorder.BOTTOM),
    BELOW_BOTTOM(TitledBorder.BELOW_BOTTOM),
}

enum class TitleJustification(val justification: Int) {
    DEFAULT(TitledBorder.DEFAULT_JUSTIFICATION),
    LEFT(TitledBorder.LEFT),
    CENTER(TitledBorder.CENTER),
    RIGHT(TitledBorder.RIGHT),
    LEADING(TitledBorder.LEADING),
    TRAILING(TitledBorder.TRAILING),
}


