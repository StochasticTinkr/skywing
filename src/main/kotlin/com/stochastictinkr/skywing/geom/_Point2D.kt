package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.Point
import java.awt.geom.Point2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.math.atan2
import kotlin.math.sqrt

inline fun point(init: Init<Point2D.Double> = {}): Point2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return Point2D.Double().also(init)
}

fun point(x: Double, y: Double) = Point2D.Double(x, y)
fun point(x: Int, y: Int) = Point(x, y)

operator fun Number.times(basis: Basis) = basis.invoke(this.toDouble())
operator fun Basis.times(number: Number) = this.invoke(number.toDouble())
operator fun Double.times(basis: Basis) = basis.invoke(this)
operator fun Basis.times(number: Double) = this.invoke(number)

operator fun Int.times(basis: Basis) = basis.invoke(this)
operator fun Basis.times(number: Int) = this.invoke(number)

operator fun Point2D.plus(other: Point2D) = point(x + other.x, y + other.y)
operator fun Point.plus(other: Point) = point(x + other.x, y + other.y)

operator fun Point2D.plusAssign(other: Point2D) = setLocation(x + other.x, y + other.y)
operator fun Point.plusAssign(other: Point) = setLocation(x + other.x, y + other.y)

operator fun Point2D.minus(other: Point2D) = point(x - other.x, y - other.y)
operator fun Point.minus(other: Point) = point(x - other.x, y - other.y)

operator fun Point2D.minusAssign(other: Point2D) = setLocation(x - other.x, y - other.y)
operator fun Point.minusAssign(other: Point) = setLocation(x - other.x, y - other.y)

operator fun Point2D.times(number: Number) = point(number.toDouble() * x, number.toDouble() * y)
operator fun Point.times(number: Int) = point(number.toDouble() * x, number.toDouble() * y)

operator fun Point2D.timesAssign(number: Number) = setLocation(number.toDouble() * x, number.toDouble() * y)
operator fun Point.timesAssign(number: Int) = setLocation(number.toDouble() * x, number.toDouble() * y)

operator fun Point2D.div(number: Number) = times(1.0 / number.toDouble())
operator fun Point2D.divAssign(number: Number) = timesAssign(1.0 / number.toDouble())

operator fun Number.times(point: Point2D) = point * this
operator fun Int.times(point: Point) = point * this

infix fun Point2D.dot(other: Point2D) = x * other.x + y * other.y
infix fun Point.dot(other: Point) = x * other.x + y * other.y

infix fun Point2D.dot(basis: Basis) = basis dot this
infix fun Point.dot(basis: Basis) = basis dot this

val Point2D.magnitudeSquared: Double get() = this dot this
val Point2D.magnitude: Double get() = sqrt(magnitudeSquared)
val Point2D.unit get() = this / magnitude
fun Point2D.unitize() = divAssign(magnitude)
fun Point2D.toDouble() = point(x, y)
fun Point2D.toInt() = point(x.toInt(), y.toInt())
fun Point2D.toAngle() = com.stochastictinkr.skywing.geom.Radians(atan2(y, x))


operator fun Point2D.component1() = x
operator fun Point2D.component2() = y

operator fun Point.component1() = x
operator fun Point.component2() = y

