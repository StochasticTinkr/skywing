package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.utils.Init
import java.awt.Component
import java.awt.Container

inline fun <C : Component> Container.addComponent(constraints: Any? = null, component: C, init: Init<C> = {}): C =
    component.apply(init).also { add(it, constraints) }
