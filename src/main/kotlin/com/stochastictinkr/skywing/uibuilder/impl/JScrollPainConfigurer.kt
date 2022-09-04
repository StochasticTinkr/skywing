package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.BorderConfig
import com.stochastictinkr.skywing.uibuilder.ComponentFactory
import com.stochastictinkr.skywing.uibuilder.JComponentConfig
import com.stochastictinkr.skywing.uibuilder.JScrollPaneConfig
import java.awt.Component
import javax.swing.JScrollPane
import javax.swing.border.Border

internal fun jScrollPainBuilder(): Builder<JScrollPaneConfig, JScrollPane> =
    JScrollPainConfigurer().asBuilder {
        JScrollPane(viewHolder.component, verticalBarPolicy.value, horizontalBarPolicy.value)
            .also { scrollPane ->
                jComponentConfigurer.configure(scrollPane)
                scrollPane.viewportBorder = viewportBorder
            }
    }


private class JScrollPainConfigurer(
    val viewHolder: ComponentHolder = ComponentHolder(),
    val jComponentConfigurer: Configurer<out JComponentConfig, in JScrollPane> = jComponentConfigurer(),
) : JScrollPaneConfig,
    ComponentFactory by viewHolder.asComponentFactory(),
    JComponentConfig by jComponentConfigurer.config {
    var viewportBorder: Border? = null
    override fun view(component: Component) {
        viewHolder.component = component
    }

    override fun view(init: ComponentFactory.() -> Component) {
        init()
    }

    override fun <T> viewportBorder(init: BorderConfig.() -> T): T =
        buildBorder({ viewportBorder = it }, init)

    override fun showHorizontalBar(init: JScrollPaneConfig.BarConfig.() -> Unit) {
        horizontalBarPolicy.init()
    }

    override fun showVerticalBar(init: JScrollPaneConfig.BarConfig.() -> Unit) {
        verticalBarPolicy.init()
    }

    abstract class BarPolicy(var value: Int) : JScrollPaneConfig.BarConfig

    var horizontalBarPolicy = object : BarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) {
        override fun always() {
            value = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        }

        override fun asNeed() {
            value = JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
        }

        override fun never() {
            value = JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        }

    }

    var verticalBarPolicy = object : BarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED) {
        override fun always() {
            value = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        }

        override fun asNeed() {
            value = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
        }

        override fun never() {
            value = JScrollPane.VERTICAL_SCROLLBAR_NEVER
        }

    }
}
