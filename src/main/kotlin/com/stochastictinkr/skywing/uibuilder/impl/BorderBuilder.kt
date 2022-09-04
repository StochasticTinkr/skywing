package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.BorderConfig
import com.stochastictinkr.skywing.uibuilder.BorderConfigSansTitled
import com.stochastictinkr.skywing.uibuilder.ColorConfig
import com.stochastictinkr.skywing.uibuilder.EtchedConfig
import com.stochastictinkr.skywing.uibuilder.FontConfig
import com.stochastictinkr.skywing.uibuilder.TitledBorderConfig
import java.awt.Color
import java.awt.Font
import javax.swing.border.BevelBorder
import javax.swing.border.Border
import javax.swing.border.EtchedBorder
import javax.swing.border.LineBorder
import javax.swing.border.SoftBevelBorder
import javax.swing.border.TitledBorder

internal fun <T> buildBorder(set: (Border?) -> Unit, init: BorderConfig.() -> T): T =
    BorderBuilder().run {
        init().also { set(border) }
    }

private class BorderBuilder : BorderConfig {
    var border: Border? = null

    override fun titled(title: String, init: TitledBorderConfig.() -> Unit): TitledBorder =
        TitledBorderBuilder(title).apply(init).border.also { border = it }

    override fun line(color: Color, thickness: Int, rounded: Boolean) {
        border = LineBorder(color, thickness, rounded)
    }

    override fun line(thickness: Int, rounded: Boolean, init: ColorConfig.() -> Unit) {
        line(buildColor(init), thickness, rounded)
    }

    override fun bevel(init: com.stochastictinkr.skywing.uibuilder.BevelConfig.() -> Unit) {
        border = BevelBuilder().apply(init).border
    }

    override fun etched(init: EtchedConfig.() -> Unit) {
        border = EtchedBuilder().apply(init).border
    }
}


private class TitledBorderBuilder(val title: String, val builder: BorderBuilder = BorderBuilder()) : TitledBorderConfig,
    BorderConfigSansTitled by builder {
    val border: TitledBorder
        get() {
            return TitledBorder(
                builder.border, title, 0, 0, font, textColor
            )
        }
    var justify: Int = TitledBorder.DEFAULT_JUSTIFICATION
    var position: Int = TitledBorder.DEFAULT_POSITION
    var font: Font? = null
    var textColor: Color? = null
    override fun justified(justify: TitledBorderConfig.Justify) {
        this.justify = when (justify) {
            TitledBorderConfig.Justify.LEFT -> TitledBorder.LEFT
            TitledBorderConfig.Justify.CENTER -> TitledBorder.CENTER
            TitledBorderConfig.Justify.RIGHT -> TitledBorder.RIGHT
            TitledBorderConfig.Justify.LEADING -> TitledBorder.LEADING
            TitledBorderConfig.Justify.TRAILING -> TitledBorder.TRAILING
        }
    }

    override fun positioned(position: TitledBorderConfig.Position) {
        this.position = when (position) {
            TitledBorderConfig.Position.ABOVE_TOP -> TitledBorder.ABOVE_TOP
            TitledBorderConfig.Position.TOP -> TitledBorder.TOP
            TitledBorderConfig.Position.BELOW_TOP -> TitledBorder.BELOW_TOP
            TitledBorderConfig.Position.ABOVE_BOTTOM -> TitledBorder.ABOVE_BOTTOM
            TitledBorderConfig.Position.BOTTOM -> TitledBorder.BOTTOM
            TitledBorderConfig.Position.BELOW_BOTTOM -> TitledBorder.BELOW_BOTTOM
        }
    }

    override fun font(font: Font) {
        this.font = font
    }

    override fun font(init: FontConfig.() -> Unit) {
        font(buildFont(init))
    }

    override fun textColor(color: Color) {
        this.textColor = color
    }

    override fun textColor(init: ColorConfig.() -> Unit) {
        textColor(buildColor(init))
    }

}

private class BevelBuilder : com.stochastictinkr.skywing.uibuilder.BevelConfig {
    val border: Border get() = borderCreate()
    var borderCreate: () -> Border = { borderFromColors.none(type) }
    var borderFromColors = BorderFromColors(::BevelBorder, ::BevelBorder, ::BevelBorder)
    var type: Int = BevelBorder.LOWERED

    private enum class NumColors {
        NONE, TWO, FOUR
    }

    class BorderFromColors(
        val none: (Int) -> Border,
        val two: (Int, Color, Color) -> Border,
        val four: (Int, Color, Color, Color, Color) -> Border,
    )

    override fun soft() {
        borderFromColors = BorderFromColors(::SoftBevelBorder, ::SoftBevelBorder, ::SoftBevelBorder)
    }

    override fun raised() {
        type = BevelBorder.RAISED
    }

    override fun lowered() {
        type = BevelBorder.LOWERED
    }

    override fun withColors(highlight: Color, shadow: Color) {
        borderCreate = { borderFromColors.two(type, highlight, shadow) }
    }

    override fun withColors(highlightOuter: Color, highlightInner: Color, shadowOuter: Color, shadowInner: Color) {
        borderCreate = { borderFromColors.four(type, highlightOuter, highlightInner, shadowOuter, shadowInner) }
    }
}

private class EtchedBuilder : EtchedConfig {
    var etchType = EtchedBorder.LOWERED
    val border: Border get() = EtchedBorder(etchType, highlight, shadow)
    var highlight: Color? = null
    var shadow: Color? = null

    override fun lowered() {
        etchType = EtchedBorder.LOWERED
    }

    override fun raised() {
        etchType = EtchedBorder.RAISED
    }

    override fun shadow(shadow: Color?) {
        this.shadow = shadow
    }

    override fun highlight(highlight: Color?) {
        this.highlight = highlight
    }

    override fun shadow(init: ColorConfig?.() -> Unit) {
        shadow(buildOptionalColor(init))
    }

    override fun highlight(init: ColorConfig?.() -> Unit) {
        highlight(buildOptionalColor(init))
    }
}

