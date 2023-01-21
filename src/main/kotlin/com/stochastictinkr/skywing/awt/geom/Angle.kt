package com.stochastictinkr.skywing.awt.geom

import java.awt.geom.Point2D
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

sealed interface Angle {
    val degrees: Double
    val radians: Double

    fun asDegrees() = Degrees(degrees)
    fun asRadians() = Radians(radians)
    operator fun plus(angle: Angle): Angle
    operator fun minus(angle: Angle): Angle
    operator fun unaryMinus(): Angle
    operator fun times(scale: Double): Angle
    operator fun div(scale: Double): Angle
    fun normalized(): Angle
}

operator fun Double.times(angle: Angle) = angle * this

data class Degrees(override val degrees: Double) : Angle {
    override val radians: Double get() = Math.toRadians(degrees)
    override fun asDegrees() = this
    override fun plus(angle: Angle) = Degrees(degrees + angle.degrees)
    override fun minus(angle: Angle) = Degrees(degrees - angle.degrees)
    override fun unaryMinus() = Degrees(-degrees)
    override fun times(scale: Double) = Degrees(degrees * scale)
    override fun div(scale: Double) = Degrees(degrees / scale)
    override fun normalized(): Angle = if (degrees < 0) Degrees(360 + (degrees % 360.0)) else Degrees(degrees % 360.0)

    override fun toString(): String {
        return "${degrees}º"
    }
}


private const val TWO_PI = PI * 2

data class Radians(override val radians: Double) : Angle {
    override val degrees: Double get() = Math.toDegrees(radians)
    override fun asRadians() = this
    override fun plus(angle: Angle) = Radians(radians + angle.radians)
    override fun minus(angle: Angle) = Radians(radians - angle.radians)
    override fun unaryMinus() = Radians(-radians)
    override fun times(scale: Double) = Radians(radians * scale)
    override fun div(scale: Double) = Radians(radians / scale)
    override fun normalized(): Angle =
        if (radians < 0) Radians(TWO_PI + (radians % TWO_PI)) else Radians(radians % TWO_PI)

    override fun toString(): String {
        return "${radians}rad"
    }
}

fun Angle.toPoint() = radians.let { point(cos(it), sin(it)) }
