package com.stochastictinkr.skywing.uibuilder

import com.stochastictinkr.skywing.rendering.PaintContext

@UiBuilderDsl
interface CustomComponentConfig : JComponentConfig {
    fun painter(painter: PaintContext.()->Unit)
}