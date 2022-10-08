package com.stochastictinkr.skywing.awt.geom

import java.awt.geom.Line2D
import java.awt.geom.Point2D

infix fun Point2D.lineTo(b: Point2D): Line2D = Line2D.Double(this, b)
inline fun line(builder: Line2D.() -> Unit) = Line2D.Double().apply(builder)

fun line(a: Point2D, b: Point2D): Line2D = a lineTo b
fun line(x1: Double, y1: Double, x2: Double, y2: Double) = point(x1, y1) lineTo point(x2, y2)
