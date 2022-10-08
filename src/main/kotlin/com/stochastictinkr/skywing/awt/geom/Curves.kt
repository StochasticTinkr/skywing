package com.stochastictinkr.skywing.awt.geom

import java.awt.geom.CubicCurve2D
import java.awt.geom.Point2D
import java.awt.geom.QuadCurve2D

fun quadCurve(p1: Point2D, cp: Point2D, p2: Point2D) = QuadCurve2D.Double(p1.x, p1.y, cp.x, cp.y, p2.x, p2.y)
fun cubicCurve(p1: Point2D, cp1: Point2D, cp2: Point2D, p2: Point2D) =
    CubicCurve2D.Double(p1.x, p1.y, cp1.x, cp1.y, cp2.x, cp2.y, p2.x, p2.y)

infix fun Point2D.toward(cp: Point2D) = WithControlPoint(this, cp)

data class WithControlPoint(val p1: Point2D, val cp: Point2D) {
    infix fun to(p2: Point2D) = quadCurve(p1, cp, p2)
    infix fun and(cp2: Point2D) = WithControlPoints(p1, cp, cp2)
}

data class WithControlPoints(val p1: Point2D, val cp1: Point2D, val cp2: Point2D) {
    infix fun to(p2: Point2D) = cubicCurve(p1, cp1, cp2, p2)
}
