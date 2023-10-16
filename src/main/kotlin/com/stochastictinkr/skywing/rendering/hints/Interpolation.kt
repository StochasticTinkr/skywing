package com.stochastictinkr.skywing.rendering.hints

import java.awt.RenderingHints

enum class Interpolation(override val value: Any) : Hint {
    NEAREST_NEIGHBOR(RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR),
    BILINEAR(RenderingHints.VALUE_INTERPOLATION_BILINEAR),
    BICUBIC(RenderingHints.VALUE_INTERPOLATION_BICUBIC),
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_INTERPOLATION
}