package com.stochastictinkr.skywing.uibuilder

import com.stochastictinkr.skywing.uibuilder.impl.buildJLabel
import java.awt.Component

@UiBuilderDsl
interface ContainerConfig {
    fun add(ref: Component)
    fun label(init: JLabelConfig.() -> Unit) = buildJLabel(init).also(::add)
}



