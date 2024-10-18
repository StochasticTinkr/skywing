package com.stochastictinkr.skywing.geom

import java.awt.geom.Dimension2D
import kotlin.math.max
import kotlin.math.min

enum class ScalingDirection {
    /**
     * Any scaling
     */
    ANY,

    /**
     * Scale > 1.0
     */
    UP,

    /**
     * Scale < 1
     */
    DOWN;

    fun matches(source: Double, target: Double) =
        when (this) {
            ANY -> true
            UP -> source < target
            DOWN -> source > target
        }

}

infix fun Double.clampedTo(direction: ScalingDirection): Double {
    return when (direction) {
        ScalingDirection.ANY -> this
        ScalingDirection.UP -> min(1.0, this)
        ScalingDirection.DOWN -> max(1.0, this)
    }
}

infix fun Dimension2D.clampedTo(direction: ScalingDirection): Dimension2D {
    return when (direction) {
        ScalingDirection.ANY -> this
        ScalingDirection.UP -> dimension(min(1.0, width), min(1.0, height))
        ScalingDirection.DOWN -> dimension(max(1.0, width), max(1.0, height))
    }
}

infix fun Double.matches(direction: ScalingDirection) =
    when (direction) {
        ScalingDirection.ANY -> true
        ScalingDirection.UP -> this > 1.0
        ScalingDirection.DOWN -> this < 1.0
    }
