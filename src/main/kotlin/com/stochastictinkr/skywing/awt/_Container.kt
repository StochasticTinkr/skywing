package com.stochastictinkr.skywing.awt

import java.awt.Component
import java.awt.Container

fun <C : Component> Container.addComponent(constraints: Any? = null, component: C, init: C.() -> Unit = {}): C =
    component.apply(init).also { add(it, constraints) }
