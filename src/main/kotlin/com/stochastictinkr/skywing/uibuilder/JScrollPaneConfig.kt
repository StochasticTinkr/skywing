package com.stochastictinkr.skywing.uibuilder

import java.awt.Component

interface JScrollPaneConfig : JComponentConfig, ComponentFactory {
    fun view(component: Component)
    fun view(init: ComponentFactory.() -> Component)
    fun viewportBorder(init: BorderConfig.()-> Unit)

    fun showHorizontalBar(init: BarConfig.() -> Unit)
    fun showVerticalBar(init: BarConfig.() -> Unit)

    interface BarConfig {
        fun always()
        fun asNeed()
        fun never()
    }
}