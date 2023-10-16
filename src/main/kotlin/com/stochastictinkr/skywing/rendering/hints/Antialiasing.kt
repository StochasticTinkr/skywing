package com.stochastictinkr.skywing.rendering.hints

import java.awt.RenderingHints

enum class Antialiasing(override val value: Any) : Hint {
    ON(RenderingHints.VALUE_ANTIALIAS_ON),
    OFF(RenderingHints.VALUE_ANTIALIAS_OFF),
    DEFAULT(RenderingHints.VALUE_ANTIALIAS_DEFAULT)
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_ANTIALIASING
}