package com.stochastictinkr.skywing.rendering.hints

import java.awt.RenderingHints

enum class Dithering(override val value: Any) : Hint {
    ENABLE(RenderingHints.VALUE_DITHER_ENABLE),
    DISABLE(RenderingHints.VALUE_DITHER_DISABLE),
    DEFAULT(RenderingHints.VALUE_DITHER_DEFAULT),
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_DITHERING
}