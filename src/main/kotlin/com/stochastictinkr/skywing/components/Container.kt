package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.utils.Init
import java.awt.Component
import java.awt.Container

/**
 * Convenience method to configure a component and add it to the receiver Container
 */
inline fun <C : Component> Container.addComponent(constraints: Any? = null, component: C, init: Init<C> = {}): C =
    component.apply(init).also { add(it, constraints) }
