package com.stochastictinkr.skywing.uibuilder

import java.awt.Color
import java.awt.Font as AwtFont

interface TitledBorderConfig : BorderConfigSansTitled {
    enum class Justify {
        LEFT,
        CENTER,
        RIGHT,
        LEADING,
        TRAILING,
    }

    enum class Position {
        ABOVE_TOP,
        TOP,
        BELOW_TOP,
        ABOVE_BOTTOM,
        BOTTOM,
        BELOW_BOTTOM,
    }

    fun justified(justify: Justify)
    fun positioned(position: Position)
    fun font(font: AwtFont)
    fun font(init: FontConfig.() -> Unit)
    fun textColor(color: Color)
    fun textColor(init: ColorConfig.() -> Unit)

    companion object {
        val left = Justify.LEFT
        val center = Justify.CENTER
        val right = Justify.RIGHT
        val leading = Justify.LEADING
        val trailing = Justify.TRAILING
        val aboveTo = Position.ABOVE_TOP
        val to = Position.TOP
        val belowTo = Position.BELOW_TOP
        val aboveBottom = Position.ABOVE_BOTTOM
        val bottom = Position.BOTTOM
        val belowBottom = Position.BELOW_BOTTOM
    }
}