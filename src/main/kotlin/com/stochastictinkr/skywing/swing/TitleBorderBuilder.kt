package com.stochastictinkr.skywing.swing

import java.awt.Color
import java.awt.Font
import javax.swing.border.Border
import javax.swing.border.TitledBorder

class TitleBorderBuilder {
    var justification: Justification = DEFAULT_JUSTIFICATION
    var position: Position = DEFAULT_POSITION

    var title = ""
    var border: Border? = null
    var font: Font? = null
    var color: Color? = null

    fun set(
        title: String = this.title,
        border: Border? = this.border,
        justification: Justification = this.justification,
        position: Position = this.position,
        font: Font? = this.font,
        color: Color? = this.color,
    ) {
        this.title = title
        this.font = font
        this.color = color
        this.border = border
        this.justification = justification
        this.position = position
    }

    companion object {
        enum class Position(val position: Int) {
            DEFAULT(TitledBorder.DEFAULT_POSITION),
            ABOVE_TOP(TitledBorder.ABOVE_TOP),
            TOP(TitledBorder.TOP),
            BELOW_TOP(TitledBorder.BELOW_TOP),
            ABOVE_BOTTOM(TitledBorder.ABOVE_BOTTOM),
            BOTTOM(TitledBorder.BOTTOM),
            BELOW_BOTTOM(TitledBorder.BELOW_BOTTOM),
        }

        enum class Justification(val justification: Int) {
            DEFAULT(TitledBorder.DEFAULT_JUSTIFICATION),
            LEFT(TitledBorder.LEFT),
            CENTER(TitledBorder.CENTER),
            RIGHT(TitledBorder.RIGHT),
            LEADING(TitledBorder.LEADING),
            TRAILING(TitledBorder.TRAILING),
        }

        val DEFAULT_POSITION = Position.DEFAULT
        val ABOVE_TOP = Position.ABOVE_TOP
        val TOP = Position.TOP
        val BELOW_TOP = Position.BELOW_TOP
        val ABOVE_BOTTOM = Position.ABOVE_BOTTOM
        val BOTTOM = Position.BOTTOM
        val BELOW_BOTTOM = Position.BELOW_BOTTOM

        val DEFAULT_JUSTIFICATION = Justification.DEFAULT
        val LEFT = Justification.LEFT
        val CENTER = Justification.CENTER
        val RIGHT = Justification.RIGHT
        val LEADING = Justification.LEADING
        val TRAILING = Justification.TRAILING
    }

    fun border(init: BorderFactorySansTitle.() -> Border) {
        border = BorderFactorySansTitle.init()
    }

    fun build() = TitledBorder(border, title, justification.justification, position.position, font, color)
}
