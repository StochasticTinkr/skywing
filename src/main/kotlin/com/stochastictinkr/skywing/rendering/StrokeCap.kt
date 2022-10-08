package com.stochastictinkr.skywing.rendering

import java.awt.BasicStroke

enum class StrokeCap(internal val value: Int) {
    BUTT(BasicStroke.CAP_BUTT),
    ROUND(BasicStroke.CAP_ROUND),
    SQUARE(BasicStroke.CAP_SQUARE),
}