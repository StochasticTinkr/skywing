package com.stochastictinkr.skywing.hints

import java.awt.RenderingHints

enum class ColorRendering(override val value: Any) : Hint {
    SPEED(RenderingHints.VALUE_COLOR_RENDER_SPEED),
    QUALITY(RenderingHints.VALUE_COLOR_RENDER_QUALITY),
    DEFAULT(RenderingHints.VALUE_COLOR_RENDER_DEFAULT),
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_COLOR_RENDERING
}