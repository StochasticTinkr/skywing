package com.stochastictinkr.skywing.geom

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
    operator fun times(scale: Int): Angle
    operator fun div(scale: Int): Angle
    operator fun times(scale: Long): Angle
    operator fun div(scale: Long): Angle
    fun normalized(): Angle

    fun component1() = radians
    fun component2() = degrees
    fun toPoint2D() = radians.let { point(cos(it), sin(it)) }
    infix fun arcTo(angle: Angle) =
        arc(startAngle = this, extentAngle = angle)
}


operator fun Double.times(angle: Angle) = angle * this
operator fun Int.times(angle: Angle) = angle * this

@JvmInline
value class Degrees(override val degrees: Double) : Angle {
    override val radians: Double get() = Math.toRadians(degrees)
    override fun asDegrees() = this
    override fun plus(angle: Angle) =
        Degrees(degrees + angle.degrees)

    override fun minus(angle: Angle) =
        Degrees(degrees - angle.degrees)

    override fun unaryMinus() = Degrees(-degrees)
    override fun times(scale: Double) = Degrees(degrees * scale)
    override fun div(scale: Double) = Degrees(degrees / scale)
    override fun times(scale: Int) = Degrees(degrees * scale)
    override fun div(scale: Int) = Degrees(degrees / scale)
    override fun times(scale: Long) = Degrees(degrees * scale)
    override fun div(scale: Long) = Degrees(degrees / scale)
    override fun normalized(): Angle = if (degrees < 0) Degrees(
        360 + (degrees % 360.0)
    ) else Degrees(degrees % 360.0)

    override fun toString(): String {
        return "${degrees}º"
    }
}

private const val TWO_PI = PI * 2

@JvmInline
value class Radians(override val radians: Double) : Angle {
    override val degrees: Double get() = Math.toDegrees(radians)
    override fun asRadians() = this
    override fun plus(angle: Angle) =
        Radians(radians + angle.radians)

    override fun minus(angle: Angle) =
        Radians(radians - angle.radians)

    override fun unaryMinus() = Radians(-radians)
    override fun times(scale: Double) = Radians(radians * scale)
    override fun div(scale: Double) = Radians(radians / scale)
    override fun times(scale: Int) = Radians(radians * scale)
    override fun div(scale: Int) = Radians(radians / scale)
    override fun times(scale: Long) = Radians(radians * scale)
    override fun div(scale: Long) = Radians(radians / scale)
    override fun normalized(): Angle =
        if (radians < 0) Radians(TWO_PI + (radians % TWO_PI)) else Radians(
            radians % TWO_PI
        )

    override fun toString(): String {
        return "${radians}rad"
    }
}


