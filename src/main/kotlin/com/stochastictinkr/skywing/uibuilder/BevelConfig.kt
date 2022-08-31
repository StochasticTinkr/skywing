package com.stochastictinkr.skywing.uibuilder

import java.awt.Color

@UiBuilderDsl
interface BevelConfig {
    fun soft()
    fun raised()
    fun lowered()
    fun withColors(highlight: Color, shadow: Color)
    fun withColors(
        highlightOuter: Color, highlightInner: Color,
        shadowOuter: Color, shadowInner: Color,
    )
}