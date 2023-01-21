package com.stochastictinkr.skywing.awt.geom

import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Path2D
import java.awt.geom.Point2D


inline fun path(start: Point2D, pathBuilder: Path2D.() -> Unit) = Path2D.Double().apply {
    moveTo(start)
    pathBuilder()
}

fun Path2D.windEvenOdd() {
    windingRule = Path2D.WIND_EVEN_ODD
}

fun Path2D.windNoZero() {
    windingRule = Path2D.WIND_NON_ZERO
}

fun Path2D.lineTo(p: Point2D) = lineTo(p.x, p.y)
fun Path2D.moveTo(p: Point2D) = moveTo(p.x, p.y)
fun Path2D.quadTo(cp: Point2D, p: Point2D) = quadTo(cp.x, cp.y, p.x, p.y)
fun Path2D.cubicTo(cp1: Point2D, cp2: Point2D, p: Point2D) = curveTo(cp1.x, cp1.y, cp2.x, cp2.y, p.x, p.y)

fun Shape.toPath(affineTransform: AffineTransform? = null) = Path2D.Double(this, affineTransform)