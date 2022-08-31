package com.stochastictinkr.skywing.uibuilder

import java.awt.Color

@UiBuilderDsl
interface EtchedConfig {
    fun lowered()
    fun raised()
    fun shadow(shadow: Color?)
    fun highlight(highlight: Color?)
    fun shadow(init: ColorConfig?.() -> Unit)
    fun highlight(init: ColorConfig?.() -> Unit)
}