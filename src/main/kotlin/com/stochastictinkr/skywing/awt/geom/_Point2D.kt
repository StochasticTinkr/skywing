package com.stochastictinkr.skywing.awt.geom

import java.awt.Point
import java.awt.geom.Point2D
import kotlin.math.atan2
import kotlin.math.sqrt

fun point(x: Double, y: Double) = Point2D.Double(x, y)
fun point(x: Float, y: Float) = Point2D.Float(x, y)
fun point(x: Int, y: Int) = Point(x, y)

sealed interface Basis {
    operator fun invoke(number: Number): Point2D
    operator fun invoke(point: Point2D): Double
}

object X : Basis {
    override fun invoke(number: Number) = point(number.toDouble(), 0.0)
    override fun invoke(point: Point2D) = point.x
}

object Y : Basis {
    override fun invoke(number: Number) = point(0.0, number.toDouble())
    override fun invoke(point: Point2D) = point.y
}

operator fun Number.times(basis: Basis) = basis(this)
operator fun Basis.times(number: Number) = this(number)
operator fun Point2D.plus(other: Point2D) = point(x + other.x, y + other.y)
operator fun Point2D.plusAssign(other: Point2D) = setLocation(x + other.x, y + other.y)
operator fun Point2D.minus(other: Point2D) = point(x - other.x, y - other.y)
operator fun Point2D.minusAssign(other: Point2D) = setLocation(x - other.x, y - other.y)
operator fun Point2D.times(number: Number) = point(number.toDouble() * x, number.toDouble() * y)
operator fun Point2D.timesAssign(number: Number) = setLocation(number.toDouble() * x, number.toDouble() * y)
operator fun Point2D.div(number: Number) = times(1.0 / number.toDouble())
operator fun Point2D.divAssign(number: Number) = timesAssign(1.0 / number.toDouble())
operator fun Number.times(point: Point2D) = point * this
infix fun Point2D.dot(other: Point2D) = x * other.x + y * other.y
infix fun Basis.dot(other: Point2D) = this(other)
infix fun Point2D.dot(basis: Basis) = basis(this)
val Point2D.magnitudeSquared: Double get() = this dot this
val Point2D.magnitude: Double get() = sqrt(magnitudeSquared)
val Point2D.unit get() = this / magnitude
val Point2D.angle get() = Radians(atan2(y, x))
fun Point2D.unitize() = this.divAssign(magnitude)

operator fun Point.plus(other: Point) = point(x + other.x, y + other.y)
operator fun Point.plusAssign(other: Point) = setLocation(x + other.x, y + other.y)
operator fun Point.minus(other: Point) = point(x - other.x, y - other.y)
operator fun Point.minusAssign(other: Point) = setLocation(x - other.x, y - other.y)
operator fun Point.times(number: Int) = point(number.toDouble() * x, number.toDouble() * y)
operator fun Point.timesAssign(number: Int) = setLocation(number.toDouble() * x, number.toDouble() * y)
operator fun Int.times(point: Point) = point * this
infix fun Point.dot(other: Point) = x * other.x + y * other.y
infix fun Basis.dot(other: Point) = this(other)
infix fun Point.dot(basis: Basis) = basis(this)
val Point.angle get() = Radians(atan2(y.toDouble(), x.toDouble()))

operator fun Point2D.component1() = x
operator fun Point2D.component2() = y

operator fun Point.component1() = x
operator fun Point.component2() = y

fun Point2D.toDouble() = point(x, y)
fun Point2D.toFloat() = point(x.toFloat(), y.toFloat())
fun Point2D.toInt() = point(x.toInt(), y.toInt())
fun Point2D.toAngle() = Radians(atan2(y, x))
