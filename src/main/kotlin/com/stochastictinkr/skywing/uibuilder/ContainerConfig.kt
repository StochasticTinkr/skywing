package com.stochastictinkr.skywing.uibuilder

import com.stochastictinkr.skywing.uibuilder.impl.componentFactory
import java.awt.Component

@UiBuilderDsl
interface ContainerConfig {
    fun add(component: Component)
    fun <T> components(init: ContainerConfig.() -> T): T = init()
    fun <T> doNotAdd(init: ComponentFactory.() -> T): T = componentFactory { }.init()
}

