@file:Suppress("unused")

package com.stochastictinkr.skywing.uibuilder

import java.awt.Color
import java.awt.Font

@UiBuilderDsl
interface BorderSpecSansTitled {
    // Line borders
    fun line(color: Color, thickness: Int = 1, rounded: Boolean = false)

    // Bevel borders
    fun bevel(spec: BevelTypeSpec.() -> Unit)
    fun softBevel(spec: BevelTypeSpec.() -> Unit) = bevel { soft(); spec() }
    fun loweredBevel(spec: BevelTypeSpec.() -> Unit = {}) = bevel { lowered(); spec() }
    fun raisedBevel(spec: BevelTypeSpec.() -> Unit = {}) = bevel { raised(); spec() }
    fun loweredBevel(highlight: Color, shadow: Color) = loweredBevel { withColors(highlight, shadow) }
    fun raisedBevel(highlight: Color, shadow: Color) = raisedBevel { withColors(highlight, shadow) }
    fun loweredBevel(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    ) = loweredBevel { withColors(highlightOuter, highlightInner, shadowOuter, shadowInner) }

    fun raisedBevel(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    ) = raisedBevel { withColors(highlightOuter, highlightInner, shadowOuter, shadowInner) }

    fun loweredSoftBevel(spec: BevelTypeSpec.() -> Unit = {}) = softBevel { lowered(); spec() }
    fun raisedSoftBevel(spec: BevelTypeSpec.() -> Unit = {}) = softBevel { raised(); spec() }
    fun loweredSoftBevel(highlight: Color, shadow: Color) = loweredSoftBevel { withColors(highlight, shadow) }
    fun raisedSoftBevel(highlight: Color, shadow: Color) = raisedSoftBevel { withColors(highlight, shadow) }
    fun loweredSoftBevel(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    ) = loweredSoftBevel { withColors(highlightOuter, highlightInner, shadowOuter, shadowInner) }

    fun raisedSoftBevel(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    ) = raisedSoftBevel { withColors(highlightOuter, highlightInner, shadowOuter, shadowInner) }


    interface BevelTypeSpec {
        fun soft()
        fun raised()
        fun lowered()
        fun withColors(highlight: Color, shadow: Color)
        fun withColors(
            highlightOuter: Color, highlightInner: Color,
            shadowOuter: Color, shadowInner: Color,
        )
    }

    // Etched borders
    fun etched(spec: EtchedTypeSpec.() -> Unit)
    fun loweredEtched(spec: EtchedTypeSpec.() -> Unit = {}) = etched { lowered(); spec() }
    fun raisedEtched(spec: EtchedTypeSpec.() -> Unit = {}) = etched { raised(); spec() }
    fun loweredEtched(highlight: Color, shadow: Color) = loweredEtched { withColors(highlight, shadow) }
    fun raisedEtched(highlight: Color, shadow: Color) = raisedEtched { withColors(highlight, shadow) }

    interface EtchedTypeSpec {
        fun lowered()
        fun raised()
        fun withColors(highlight: Color, shadow: Color)
    }
}

@UiBuilderDsl
interface BorderSpec : BorderSpecSansTitled {
    fun titled(title: String, spec: TitledBorderSpec.() -> Unit = {})
    interface TitledBorderSpec : BorderSpecSansTitled {
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
        fun font(font: Font)
        fun font(spec: FontSpec.() -> Unit)
        fun textColor(color: Color)

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
}
