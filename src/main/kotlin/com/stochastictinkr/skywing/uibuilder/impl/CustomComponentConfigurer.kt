package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.rendering.CustomComponent
import com.stochastictinkr.skywing.rendering.PaintContext
import com.stochastictinkr.skywing.uibuilder.CustomComponentConfig
import com.stochastictinkr.skywing.uibuilder.JComponentConfig

internal fun customComponentBuilder(): Builder<CustomComponentConfig, CustomComponent> =
    CustomComponentConfigurer().asBuilder {
        CustomComponent(painter ?: throw IllegalStateException("You must specify a painter"))
            .also(jComponentConfigurer.configure)
    }

private class CustomComponentConfigurer(
    var painter: (PaintContext.() -> Unit)? = null,
    val jComponentConfigurer: Configurer<out JComponentConfig, in CustomComponent> = jComponentConfigurer(),
) :
    CustomComponentConfig, JComponentConfig by jComponentConfigurer.config {
    override fun painter(painter: PaintContext.() -> Unit) {
        this.painter = painter
    }
}