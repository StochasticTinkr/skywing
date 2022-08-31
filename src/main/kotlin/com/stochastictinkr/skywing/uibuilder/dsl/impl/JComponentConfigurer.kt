package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.uibuilder.dsl.BorderConfig
import com.stochastictinkr.skywing.uibuilder.dsl.ComponentConfig
import com.stochastictinkr.skywing.uibuilder.dsl.JComponentConfig
import com.stochastictinkr.skywing.uibuilder.dsl.ManagedLayoutConfig
import java.awt.Component
import javax.swing.JComponent
import javax.swing.border.Border

fun jComponentConfigurer(): Configurer<JComponentConfig, JComponent> =
    JComponentConfigurer().asConfigurer {
        componentConfigurer.configure(it)
        managedLayoutConfigurer.configure(it)
        it.border = border
    }

private class JComponentConfigurer(
    val componentConfigurer: Configurer<ComponentConfig, Component> = componentConfigurer(),
    val managedLayoutConfigurer: Configurer<ManagedLayoutConfig, in JComponent> = containerConfigurer(),
) : JComponentConfig,
    ComponentConfig by componentConfigurer.config,
    ManagedLayoutConfig by managedLayoutConfigurer.config {

    var border: Border? = null
    override fun border(border: Border?) {
        this.border = border
    }

    override fun border(init: BorderConfig.() -> Unit) {
        border(buildBorder(init))
    }
}