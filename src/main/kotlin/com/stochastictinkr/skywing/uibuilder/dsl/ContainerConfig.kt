package com.stochastictinkr.skywing.uibuilder.dsl

import com.stochastictinkr.skywing.uibuilder.dsl.impl.buildJLabel
import java.awt.Component

@UiBuilderDsl
interface ContainerConfig {
    fun add(ref: Component)
    fun label(init: JLabelConfig.() -> Unit) = buildJLabel(init).also(::add)
}



