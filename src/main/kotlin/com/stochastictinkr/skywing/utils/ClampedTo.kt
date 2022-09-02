package com.stochastictinkr.skywing.utils

infix fun Float.clampedTo(range: ClosedRange<Float>): Float {
    return when {
        range.start > this -> range.start
        range.endInclusive < this -> range.endInclusive
        else -> this
    }
}

infix fun Int.clampedTo(range: ClosedRange<Int>): Int {
    return when {
        range.start > this -> range.start
        range.endInclusive < this -> range.endInclusive
        else -> this
    }
}
