package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.BoxConfig
import com.stochastictinkr.skywing.uibuilder.ComponentFactory
import com.stochastictinkr.skywing.uibuilder.JLabelConfig
import com.stochastictinkr.skywing.uibuilder.JScrollPaneConfig
import com.stochastictinkr.skywing.uibuilder.JSliderConfig
import java.awt.Component
import javax.swing.Box
import javax.swing.JLabel
import javax.swing.JScrollPane
import javax.swing.JSlider

internal fun componentFactory(consumer: (Component) -> Unit): ComponentFactory = ComponentFactoryImpl(consumer)

private class ComponentFactoryImpl(val consumer: (Component) -> Unit) : ComponentFactory {
    override fun label(init: JLabelConfig.() -> Unit): JLabel = build(jLabelBuilder(), init)
    override fun slider(init: JSliderConfig.() -> Unit): JSlider = build(jSliderBuilder(), init)
    override fun box(init: BoxConfig.() -> Unit): Box = build(boxBuilder(), init)
    override fun scrollPane(init: JScrollPaneConfig.() -> Unit): JScrollPane = build(jScrollPainBuilder(), init)

    private fun <Config, T : Component> build(builder: Builder<Config, T>, init: Config.() -> Unit): T {
        builder.config.init()
        return builder.build().also(consumer)
    }
}