package com.stochastictinkr.skywing.awt

import java.awt.Component
import java.awt.Container

fun <C : Component> Container.add(constraints: Any? = null, component: C, init: C.() -> Unit = {}) =
    component.also(init).also { add(it, constraints) }

fun <C : Component> Container.add(constraints: Any? = null, create: () -> C) =
    create().also { add(it, constraints) }