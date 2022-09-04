package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.ComponentFactory
import com.stochastictinkr.skywing.uibuilder.ManagedLayoutConfig
import java.awt.Component
import java.awt.Container

internal fun containerConfigurer(): Configurer<ManagedLayoutConfig, Container> =
    ContainerConfigurer().asConfigurer { container ->
        items.forEach { addItem -> container.addItem() }
    }

private class ContainerConfigurer(
    val items: MutableList<Container.() -> Unit> = mutableListOf(),
) : ManagedLayoutConfig, ComponentFactory by (addToItems(items)) {

    override fun add(component: Component, constraints: Any?) {
        items.add { add(component, constraints) }
    }

    override fun <T> withConstraint(constraints: Any?, init: ComponentFactory.() -> T): T =
        componentFactory { component -> items.add { add(component, constraints) } }.init()

    override fun <T> components(init: ComponentFactory.() -> T): T = init()
}

private fun addToItems(items: MutableList<Container.() -> Unit>) =
    componentFactory { component -> items.add { add(component) } }