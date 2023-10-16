package com.stochastictinkr.skywing.geom

import java.awt.Point
import java.awt.geom.Point2D


sealed interface Basis {
    operator fun invoke(number: Double): Point2D
    operator fun invoke(number: Int): Point
    operator fun invoke(point: Point2D): Double
    operator fun invoke(point: Point): Int
    infix fun dot(point: Point2D) = this(point)
    infix fun dot(point: Point) = this(point)
}

data object X : Basis {
    override fun invoke(number: Double) = point(number, 0.0)
    override fun invoke(number: Int) = point(number, 0)
    override fun invoke(point: Point2D) = point.x
    override fun invoke(point: Point) = point.x
}

data object Y : Basis {
    override fun invoke(number: Double) = point(0.0, number)
    override fun invoke(number: Int) = point(0, number)
    override fun invoke(point: Point2D) = point.y
    override fun invoke(point: Point) = point.y
}