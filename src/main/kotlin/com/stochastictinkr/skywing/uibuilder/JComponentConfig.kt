package com.stochastictinkr.skywing.uibuilder

import javax.swing.border.Border

@UiBuilderDsl
interface JComponentConfig : ComponentConfig {

    fun alignmentX(weight: Float)
    fun alignmentY(weight: Float)
    fun alignment(xWeight: Float, yWeight: Float)

    fun border(border: Border?)
    fun border(init: BorderConfig.() -> Unit)

    fun toolTip(text: String)
}