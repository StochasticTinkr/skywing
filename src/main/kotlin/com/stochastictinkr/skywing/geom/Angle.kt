package com.stochastictinkr.skywing.geom

import java.awt.Graphics2D
import java.awt.geom.AffineTransform
import java.awt.geom.Point2D
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

sealed interface Angle {
    val degrees: Double
    val radians: Double

    fun asDegrees() = com.stochastictinkr.skywing.geom.Degrees(degrees)
    fun asRadians() = com.stochastictinkr.skywing.geom.Radians(radians)
    operator fun plus(angle: com.stochastictinkr.skywing.geom.Angle): com.stochastictinkr.skywing.geom.Angle
    operator fun minus(angle: com.stochastictinkr.skywing.geom.Angle): com.stochastictinkr.skywing.geom.Angle
    operator fun unaryMinus(): com.stochastictinkr.skywing.geom.Angle
    operator fun times(scale: Double): com.stochastictinkr.skywing.geom.Angle
    operator fun div(scale: Double): com.stochastictinkr.skywing.geom.Angle
    fun normalized(): com.stochastictinkr.skywing.geom.Angle

    fun component1() = radians
    fun component2() = degrees
    fun toPoint2D() = radians.let { com.stochastictinkr.skywing.geom.point(cos(it), sin(it)) }
    infix fun arcTo(angle: com.stochastictinkr.skywing.geom.Angle) =
        com.stochastictinkr.skywing.geom.arc(startAngle = this, extentAngle = angle)
}


operator fun Double.times(angle: com.stochastictinkr.skywing.geom.Angle) = angle * this

data class Degrees(override val degrees: Double) : com.stochastictinkr.skywing.geom.Angle {
    override val radians: Double get() = Math.toRadians(degrees)
    override fun asDegrees() = this
    override fun plus(angle: com.stochastictinkr.skywing.geom.Angle) =
        com.stochastictinkr.skywing.geom.Degrees(degrees + angle.degrees)
    override fun minus(angle: com.stochastictinkr.skywing.geom.Angle) =
        com.stochastictinkr.skywing.geom.Degrees(degrees - angle.degrees)
    override fun unaryMinus() = com.stochastictinkr.skywing.geom.Degrees(-degrees)
    override fun times(scale: Double) = com.stochastictinkr.skywing.geom.Degrees(degrees * scale)
    override fun div(scale: Double) = com.stochastictinkr.skywing.geom.Degrees(degrees / scale)
    override fun normalized(): com.stochastictinkr.skywing.geom.Angle = if (degrees < 0) com.stochastictinkr.skywing.geom.Degrees(
        360 + (degrees % 360.0)
    ) else com.stochastictinkr.skywing.geom.Degrees(degrees % 360.0)

    override fun toString(): String {
        return "${degrees}º"
    }
}


private const val TWO_PI = PI * 2

data class Radians(override val radians: Double) : com.stochastictinkr.skywing.geom.Angle {
    override val degrees: Double get() = Math.toDegrees(radians)
    override fun asRadians() = this
    override fun plus(angle: com.stochastictinkr.skywing.geom.Angle) =
        com.stochastictinkr.skywing.geom.Radians(radians + angle.radians)
    override fun minus(angle: com.stochastictinkr.skywing.geom.Angle) =
        com.stochastictinkr.skywing.geom.Radians(radians - angle.radians)
    override fun unaryMinus() = com.stochastictinkr.skywing.geom.Radians(-radians)
    override fun times(scale: Double) = com.stochastictinkr.skywing.geom.Radians(radians * scale)
    override fun div(scale: Double) = com.stochastictinkr.skywing.geom.Radians(radians / scale)
    override fun normalized(): com.stochastictinkr.skywing.geom.Angle =
        if (radians < 0) com.stochastictinkr.skywing.geom.Radians(com.stochastictinkr.skywing.geom.TWO_PI + (radians % com.stochastictinkr.skywing.geom.TWO_PI)) else com.stochastictinkr.skywing.geom.Radians(
            radians % com.stochastictinkr.skywing.geom.TWO_PI
        )

    override fun toString(): String {
        return "${radians}rad"
    }
}


