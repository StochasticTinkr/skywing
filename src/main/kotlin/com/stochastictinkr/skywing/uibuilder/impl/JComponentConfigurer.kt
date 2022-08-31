package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.ComponentConfig
import com.stochastictinkr.skywing.uibuilder.JComponentConfig
import com.stochastictinkr.skywing.uibuilder.ManagedLayoutConfig
import java.awt.Component
import javax.swing.JComponent
import javax.swing.border.Border

internal fun jComponentConfigurer(): Configurer<JComponentConfig, JComponent> =
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
    var toolTipText: String? = null
    var border: Border? = null
    override fun border(border: Border?) {
        this.border = border
    }

    override fun border(init: com.stochastictinkr.skywing.uibuilder.BorderConfig.() -> Unit) {
        border(buildBorder(init))
    }

    override fun toolTip(text: String) {
        toolTipText = text
    }
}