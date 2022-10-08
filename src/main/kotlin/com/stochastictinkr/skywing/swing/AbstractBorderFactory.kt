package com.stochastictinkr.skywing.swing

import java.awt.Color
import javax.swing.border.BevelBorder
import javax.swing.border.EtchedBorder
import javax.swing.border.LineBorder
import javax.swing.border.SoftBevelBorder

sealed class AbstractBorderFactory {

    fun line(color: Color? = null, thickness: Int = 1, roundedCorners: Boolean) =
        LineBorder(color, thickness, roundedCorners)

    fun bevel(init: BevelBorderBuilder.() -> Unit) = BevelBorderBuilder().apply(init).build()
    fun etched(init: EtchedBorderBuilder.() -> Unit) = EtchedBorderBuilder().apply(init).build()

    class EtchedBorderBuilder {
        private var type = EtchedBorder.LOWERED
        var highlight: Color? = null
        var shadow: Color? = null
        fun raised() {
            type = EtchedBorder.RAISED
        }

        fun lowered() {
            type = EtchedBorder.LOWERED
        }

        fun defaultColors() {
            colors(null, null)
        }

        fun colors(highlight: Color?, shadow: Color?) {
            this.highlight = highlight
            this.shadow = shadow
        }

        fun build() = EtchedBorder(type, highlight, shadow)
    }

    class BevelBorderBuilder {
        private var type = BevelBorder.RAISED
        private var factory: (
            bevelType: Int,
            highlightOuterColor: Color?,
            highlightInnerColor: Color?, shadowOuterColor: Color?,
            shadowInnerColor: Color?,
        ) -> BevelBorder = ::BevelBorder

        var highlightOuterColor: Color? = null
        var highlightInnerColor: Color? = null
        var shadowOuterColor: Color? = null
        var shadowInnerColor: Color? = null

        fun defaultColors() {
            colors(null, null)
        }

        fun colors(highlight: Color?, shadow: Color?) {
            highlight(highlight)
            shadow(shadow)
        }

        private fun highlight(highlightOuterColor: Color? = null, highlightInnerColor: Color? = null) {
            this.highlightOuterColor = highlightOuterColor ?: highlightInnerColor?.brighter()
            this.highlightInnerColor = highlightInnerColor
        }

        private fun shadow(shadowOuterColor: Color?, shadowInnerColor: Color? = null) {
            this.shadowInnerColor = shadowInnerColor
            this.shadowOuterColor = shadowOuterColor ?: shadowInnerColor?.brighter()
        }

        fun raised() {
            type = BevelBorder.RAISED
        }

        fun lowered() {
            type = BevelBorder.LOWERED
        }

        fun soft() {
            factory = ::SoftBevelBorder
        }

        fun hard() {
            factory = ::BevelBorder
        }

        fun build() = factory(type, highlightOuterColor, highlightInnerColor, shadowOuterColor, shadowInnerColor)
    }
}