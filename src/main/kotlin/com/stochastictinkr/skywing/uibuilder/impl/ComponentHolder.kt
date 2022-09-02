package com.stochastictinkr.skywing.uibuilder.impl

import java.awt.Component

internal class ComponentHolder(var component: Component? = null) {
    fun asComponentFactory() = componentFactory { component = it }
}