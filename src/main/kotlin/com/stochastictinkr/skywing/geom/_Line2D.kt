package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.geom.Line2D
import java.awt.geom.Point2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun line(init: Init<Line2D>): Line2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return Line2D.Double().apply(init)
}

fun line(x1: Double = 0.0, y1: Double = 0.0, x2: Double = 0.0, y2: Double = 0.0) =
    Line2D.Double(x1, y1, x2, y2)

fun line(a: Point2D, b: Point2D) = line(a.x, a.y, b.x, b.y)

infix fun Point2D.lineTo(b: Point2D) = line(this, b)

fun Line2D.component1(): Point2D = this.p1
fun Line2D.component2(): Point2D = this.p2

fun Line2D.copy(x1: Double = this.x1, y1: Double = this.y1, x2: Double = this.x2, y2: Double = this.y2) =
    line(x1, y1, x2, y2)

fun Line2D.copy(p1: Point2D = this.p1, p2: Point2D = this.p2) = line(p1, p2)
