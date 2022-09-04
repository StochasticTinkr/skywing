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
        alignmentX?.let { component.alignmentX = it }
        alignmentY?.let { component.alignmentY = it }
        component.border = border
        component.toolTipText = toolTipText
    }

internal fun jComponentConfigurerWithContainer(): Configurer<ContainerAndJComponentConfig, JComponent> =
    ContainerAndJComponentConfigurer().asConfigurer { component ->
        jComponentConfigurer.configure(component)
        managedLayoutConfigurer.configure(component)
    }

internal interface ContainerAndJComponentConfig : JComponentConfig, ManagedLayoutConfig

private class ContainerAndJComponentConfigurer(
    val jComponentConfigurer: Configurer<out JComponentConfig, in JComponent> = jComponentConfigurer(),
    val managedLayoutConfigurer: Configurer<out ManagedLayoutConfig, in JComponent> = containerConfigurer(),
) : ContainerAndJComponentConfig, JComponentConfig by jComponentConfigurer.config,
    ManagedLayoutConfig by managedLayoutConfigurer.config

private class JComponentConfigurer(
    val componentConfigurer: Configurer<ComponentConfig, Component> = componentConfigurer(),
) : JComponentConfig, ComponentConfig by componentConfigurer.config {
    var toolTipText: String? = null
    var border: Border? = null

    var alignmentX: Float? = null
    var alignmentY: Float? = null
    override fun alignmentX(weight: Float) {
        alignmentX = weight
    }

    override fun alignmentY(weight: Float) {
        alignmentY = weight
    }

    override fun alignment(xWeight: Float, yWeight: Float) {
        alignmentX(xWeight)
        alignmentY(yWeight)
    }

    override fun border(border: Border?) {
        this.border = border
    }

    override fun <T> border(init: com.stochastictinkr.skywing.uibuilder.BorderConfig.() -> T): T =
        buildBorder(::border, init)

    override fun toolTip(text: String) {
        toolTipText = text
    }
}