package com.stochastictinkr.skywing.hints

import java.awt.RenderingHints

enum class Rendering(override val value: Any) : Hint {
    SPEED(RenderingHints.VALUE_RENDER_SPEED),
    QUALITY(RenderingHints.VALUE_RENDER_QUALITY),
    DEFAULT(RenderingHints.VALUE_RENDER_DEFAULT),
    ;

    override val key: RenderingHints.Key get() = RenderingHints.KEY_RENDERING
}

