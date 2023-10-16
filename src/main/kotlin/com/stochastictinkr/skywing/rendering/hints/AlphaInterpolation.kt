package com.stochastictinkr.skywing.rendering.hints

import java.awt.RenderingHints

enum class AlphaInterpolation(override val value: Any) : Hint {
    SPEED(RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED),
    QUALITY(RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY),
    DEFAULT(RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT),
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_ALPHA_INTERPOLATION
}