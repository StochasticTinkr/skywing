package com.stochastictinkr.skywing.rendering.hints

import java.awt.RenderingHints

enum class FractionalMetrics(override val value: Any) : Hint {
    ON(RenderingHints.VALUE_FRACTIONALMETRICS_ON),
    OFF(RenderingHints.VALUE_FRACTIONALMETRICS_OFF),
    DEFAULT(RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT),
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_FRACTIONALMETRICS
}