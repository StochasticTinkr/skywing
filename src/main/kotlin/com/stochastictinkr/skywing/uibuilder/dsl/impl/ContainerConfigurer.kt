package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.uibuilder.dsl.ManagedLayoutConfig
import java.awt.Component
import java.awt.Container

fun containerConfigurer(): Configurer<ManagedLayoutConfig, Container> =
    ContainerConfigurer().asConfigurer { container ->
        items.forEach { addItem -> container.addItem() }
    }

private class ContainerConfigurer : ManagedLayoutConfig {
    val items = mutableListOf<Container.() -> Unit>()

    override fun add(ref: Component) {
        items.add { add(ref) }
    }
}