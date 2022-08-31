package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.uibuilder.dsl.BorderConfig
import com.stochastictinkr.skywing.uibuilder.dsl.ComponentConfig
import com.stochastictinkr.skywing.uibuilder.dsl.JComponentConfig
import com.stochastictinkr.skywing.uibuilder.dsl.ManagedLayoutConfig
import java.awt.Component
import javax.swing.JComponent
import javax.swing.border.Border

fun jComponentConfigurer(): Configurer<JComponentConfig, JComponent> =
    JComponentConfigurer().asConfigurer { component ->
        componentConfigurer.configure(component)
        managedLayoutConfigurer.configure(component)
        component.border = border
        component.toolTipText = toolTipText
    }

private class JComponentConfigurer(
    val componentConfigurer: Configurer<ComponentConfig, Component> = componentConfigurer(),
    val managedLayoutConfigurer: Configurer<ManagedLayoutConfig, in JComponent> = containerConfigurer(),
) : JComponentConfig,
    ComponentConfig by componentConfigurer.config,
    ManagedLayoutConfig by managedLayoutConfigurer.config {
    var toolTipText:String? = null
    var border: Border? = null
    override fun border(border: Border?) {
        this.border = border
    }

    override fun border(init: BorderConfig.() -> Unit) {
        border(buildBorder(init))
    }

    override fun toolTip(text: String) {
        toolTipText = text
    }
}