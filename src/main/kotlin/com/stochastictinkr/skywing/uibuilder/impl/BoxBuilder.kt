package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.BoxConfig
import javax.swing.Box
import javax.swing.BoxLayout

internal fun boxBuilder(): Builder<BoxConfig, Box> =
    BoxBuilder().asBuilder { Box(axis).also(containerAndJComponentConfigurer.configure) }

private class BoxBuilder(val containerAndJComponentConfigurer: Configurer<out ContainerAndJComponentConfig, in Box> = jComponentConfigurerWithContainer()) :
    BoxConfig,
    ContainerAndJComponentConfig by containerAndJComponentConfigurer.config {
    var axis = BoxLayout.PAGE_AXIS
    override fun page() {
        axis = BoxLayout.PAGE_AXIS
    }

    override fun line() {
        axis = BoxLayout.LINE_AXIS
    }

    override fun vertical() {
        axis = BoxLayout.Y_AXIS
    }

    override fun horizontal() {
        axis = BoxLayout.X_AXIS
    }
}