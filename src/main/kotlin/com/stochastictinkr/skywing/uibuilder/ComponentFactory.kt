package com.stochastictinkr.skywing.uibuilder

import com.stochastictinkr.skywing.rendering.CustomComponent
import com.stochastictinkr.skywing.uibuilder.impl.componentFactory
import java.awt.Component
import javax.swing.Box
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JScrollPane
import javax.swing.JSlider

@UiBuilderDsl
interface ComponentFactory {
    fun label(init: JLabelConfig.() -> Unit): JLabel
    fun slider(init: JSliderConfig.() -> Unit): JSlider
    fun box(init: BoxConfig.() -> Unit): Box
    fun scrollPane(init: JScrollPaneConfig.() -> Unit): JScrollPane

    fun custom(init: CustomComponentConfig.() -> Unit): CustomComponent

    fun verticalGlue(): Component
    fun horizontalGlue(): Component
    fun verticalStrut(px: Int): Component
    fun horizontalStrut(px: Int): Component
}

fun <T> components(init: ComponentFactory.() -> T): T = componentFactory { }.init()
