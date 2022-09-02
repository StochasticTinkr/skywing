package com.stochastictinkr.skywing.uibuilder

import javax.swing.border.Border

@UiBuilderDsl
interface JComponentConfig : ComponentConfig {
    fun border(border: Border?)
    fun border(init: BorderConfig.() -> Unit)

    fun toolTip(text: String)
}