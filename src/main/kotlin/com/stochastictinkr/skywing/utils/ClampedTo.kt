package com.stochastictinkr.skywing.utils

infix fun Float.clampedTo(range: ClosedFloatingPointRange<Float>): Float {
    if (range.contains(this)) {
        return this
    }
    if (range.start > this) {
        return range.start
    } else return range.endInclusive
}