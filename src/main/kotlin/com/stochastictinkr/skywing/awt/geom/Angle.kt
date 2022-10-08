package com.stochastictinkr.skywing.awt.geom

import kotlin.math.PI

sealed interface Angle {
    val degrees: Double
    val radians: Double

    fun asDegrees() = Degrees(degrees)
    fun asRadians() = Radians(radians)
}

private const val radiansPerDegree = PI / 180
private const val degreesPerRadians = 180 / PI

data class Degrees(override val degrees: Double) : Angle {
    override val radians: Double get() = degrees * radiansPerDegree
    override fun asDegrees() = this
}

data class Radians(override val radians: Double) : Angle {
    override val degrees: Double get() = radians * degreesPerRadians
    override fun asRadians() = this
}