package com.stochastictinkr.skywing.rendering

import java.awt.BasicStroke

enum class StrokeJoin(internal val value: Int) {
    MITER(BasicStroke.JOIN_MITER),
    ROUND(BasicStroke.JOIN_ROUND),
    BEVEL(BasicStroke.JOIN_BEVEL),
}