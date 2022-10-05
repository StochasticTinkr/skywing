package com.stochastictinkr.skywing.hints

import java.awt.RenderingHints

enum class StrokeControl(override val value: Any) : Hint {
    NORMALIZE(RenderingHints.VALUE_STROKE_NORMALIZE),
    PURE(RenderingHints.VALUE_STROKE_PURE),
    DEFAULT(RenderingHints.VALUE_STROKE_DEFAULT),
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_STROKE_CONTROL
}