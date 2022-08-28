package com.stoachstictinkr.skywing.com.stoachstictinkr.skywing.com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.uibuilder.BorderSpec
import com.stoachstictinkr.skywing.uibuilder.BorderSpecSansTitled
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import java.awt.Color
import java.awt.Font
import javax.swing.border.BevelBorder
import javax.swing.border.Border
import javax.swing.border.EtchedBorder
import javax.swing.border.LineBorder
import javax.swing.border.SoftBevelBorder
import javax.swing.border.TitledBorder

class BorderBuilder : BorderSpec {
    private var border: Border? = null

    override fun titled(title: String, spec: BorderSpec.TitledBorderSpec.() -> Unit) {
        border = TitledBorderBuilder(title).apply(spec).build()
    }

    class TitledBorderBuilder(val title: String) : BorderSpec.TitledBorderSpec {
        private var font: Font? = null
        private var color: Color? = null
        private var justify: Int = TitledBorder.DEFAULT_JUSTIFICATION
        private var position: Int = TitledBorder.DEFAULT_POSITION
        private var border: Border? = null
        override fun border(spec: BorderSpecSansTitled.() -> Unit) {
            border = BorderBuilder().apply(spec).border
        }

        override fun justified(justify: BorderSpec.TitledBorderSpec.Justify) {
            this.justify = when (justify) {
                BorderSpec.TitledBorderSpec.Justify.LEFT -> TitledBorder.LEFT
                BorderSpec.TitledBorderSpec.Justify.CENTER -> TitledBorder.CENTER
                BorderSpec.TitledBorderSpec.Justify.RIGHT -> TitledBorder.RIGHT
                BorderSpec.TitledBorderSpec.Justify.LEADING -> TitledBorder.LEADING
                BorderSpec.TitledBorderSpec.Justify.TRAILING -> TitledBorder.TRAILING
            }
        }

        override fun positioned(position: BorderSpec.TitledBorderSpec.Position) {
            this.position = when (position) {
                BorderSpec.TitledBorderSpec.Position.ABOVE_TOP -> TitledBorder.ABOVE_TOP
                BorderSpec.TitledBorderSpec.Position.TOP -> TitledBorder.TOP
                BorderSpec.TitledBorderSpec.Position.BELOW_TOP -> TitledBorder.BELOW_TOP
                BorderSpec.TitledBorderSpec.Position.ABOVE_BOTTOM -> TitledBorder.ABOVE_BOTTOM
                BorderSpec.TitledBorderSpec.Position.BOTTOM -> TitledBorder.BOTTOM
                BorderSpec.TitledBorderSpec.Position.BELOW_BOTTOM -> TitledBorder.BELOW_BOTTOM
            }
        }

        override fun font(font: Font) {
            this.font = font
        }

        override fun color(color: Color) {
            this.color = color
        }

        fun build(): Border {
            return TitledBorder(border, title, justify, position, font, color)
        }
    }

    override fun lineBorder(color: Color, thickness: Int, rounded: Boolean) {
        border = LineBorder(color, thickness, rounded)
    }

    override fun bevel(spec: BorderSpecSansTitled.BevelTypeSpec.() -> Unit) {
        border = BevelBorderBuilder().apply(spec).build()
    }

    class BevelBorderBuilder : BorderSpecSansTitled.BevelTypeSpec {
        private var type: Int = BevelBorder.LOWERED
        private var isSoft: Boolean = false
        private var highlight: Color? = null
        private var highlightOuter: Color? = null
        private var highlightInner: Color? = null
        private var shadowOuter: Color? = null
        private var shadowInner: Color? = null
        private var shadow: Color? = null
        private var constructorSelector: ConstructorSelector = ConstructorSelector.NO_COLORS

        private enum class ConstructorSelector {
            NO_COLORS,
            TWO_COLORS,
            FOUR_COLORS
        }

        override fun raised() {
            type = BevelBorder.RAISED
        }

        override fun lowered() {
            type = BevelBorder.LOWERED
        }

        override fun soft() {
            isSoft = true
        }

        override fun withColors(highlight: Color, shadow: Color) {
            this.highlight = highlight
            this.shadow = shadow
            constructorSelector = ConstructorSelector.TWO_COLORS
        }

        override fun withColors(highlightOuter: Color, highlightInner: Color, shadowOuter: Color, shadowInner: Color) {
            this.highlightOuter = highlightOuter
            this.highlightInner = highlightInner
            this.shadowOuter = shadowOuter
            this.shadowInner = shadowInner
            constructorSelector = ConstructorSelector.FOUR_COLORS
        }

        fun build(): Border {
            data class Builder(
                val noColors: (Int) -> Border,
                val twoColors: (Int, Color?, Color?) -> Border,
                val fourColors: (Int, Color?, Color?, Color?, Color?) -> Border,
            )

            val builder = if (isSoft) Builder(::SoftBevelBorder, ::SoftBevelBorder, ::SoftBevelBorder) else
                Builder(::BevelBorder, ::BevelBorder, ::BevelBorder)

            return when (constructorSelector) {
                ConstructorSelector.NO_COLORS -> builder.noColors(type)
                ConstructorSelector.TWO_COLORS -> builder.twoColors(type, highlight, shadow)
                ConstructorSelector.FOUR_COLORS -> builder.fourColors(
                    type,
                    highlightOuter,
                    highlightInner,
                    shadowOuter,
                    shadowInner
                )
            }
        }
    }

    override fun etched(spec: BorderSpecSansTitled.EtchedTypeSpec.() -> Unit) {
        border = EtchedBuilder().apply(spec).build()
    }

    private class EtchedBuilder() : BorderSpecSansTitled.EtchedTypeSpec {
        private var type: Int = EtchedBorder.LOWERED
        private var highlight: Color? = null
        private var shadow: Color? = null
        override fun lowered() {
            type = EtchedBorder.LOWERED
        }

        override fun raised() {
            type = EtchedBorder.RAISED
        }

        override fun withColors(highlight: Color, shadow: Color) {
            this.highlight = highlight
            this.shadow = shadow
        }

        fun build(): Border {
            return EtchedBorder(type, highlight, shadow)
        }
    }

    override fun getInstance(resolver: SpecResolver): Border? = border

}
