package com.stochastictinkr.skywing.rendering.hints

import java.awt.RenderingHints

enum class ResolutionVariant(override val value: Any) : Hint {
    DEFAULT(RenderingHints.VALUE_RESOLUTION_VARIANT_DEFAULT),
    BASE(RenderingHints.VALUE_RESOLUTION_VARIANT_BASE),
    SIZE_FIT(RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT),
    DPI_FIT(RenderingHints.VALUE_RESOLUTION_VARIANT_DPI_FIT),
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_RESOLUTION_VARIANT
}