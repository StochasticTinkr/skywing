package com.stochastictinkr.skywing.uibuilder

import com.stochastictinkr.skywing.uibuilder.impl.componentFactory
import java.awt.Component

@UiBuilderDsl
interface ContainerConfig {
    fun add(component: Component, constraints: Any? = null)
    fun <T> withConstraint(constraints: Any? = null, init: ComponentFactory.() -> T):T
    fun <T> components(init: ComponentFactory.() -> T): T
    fun <T> doNotAdd(init: ComponentFactory.() -> T): T = componentFactory { }.init()
}

