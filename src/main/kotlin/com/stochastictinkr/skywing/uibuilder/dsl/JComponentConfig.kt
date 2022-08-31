package com.stochastictinkr.skywing.uibuilder.dsl

import javax.swing.JLabel
import javax.swing.border.Border

@UiBuilderDsl
interface JComponentConfig : ComponentConfig, ManagedLayoutConfig {
    fun border(border: Border?)
    fun border(init: BorderConfig.() -> Unit)
}