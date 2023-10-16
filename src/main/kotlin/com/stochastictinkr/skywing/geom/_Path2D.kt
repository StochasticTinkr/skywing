package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.Shape
import java.awt.geom.AffineTransform
import java.awt.geom.Path2D
import java.awt.geom.Point2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


inline fun path(start: Point2D, init: Init<Path2D> = {}): Path2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return Path2D.Double().apply {
        moveTo(start)
        init()
    }
}

fun Path2D.windEvenOdd() {
    windingRule = Path2D.WIND_EVEN_ODD
}

fun Path2D.windNonZero() {
    windingRule = Path2D.WIND_NON_ZERO
}

infix fun Path2D.moveTo(p: Point2D) = apply { moveTo(p.x, p.y) }
infix fun Path2D.lineTo(p: Point2D) = apply { lineTo(p.x, p.y) }
fun Path2D.quadTo(cp: Point2D, p: Point2D) = apply { quadTo(cp.x, cp.y, p.x, p.y) }
fun Path2D.cubicTo(cp1: Point2D, cp2: Point2D, p: Point2D) = apply { curveTo(cp1.x, cp1.y, cp2.x, cp2.y, p.x, p.y) }

fun Shape.toPath(affineTransform: AffineTransform? = null) = Path2D.Double(this, affineTransform)

infix fun Path2D.toward(cp: Point2D) = PathWithControlPoint(this, cp)

data class PathWithControlPoint(val path: Path2D, val cp: Point2D) {
    infix fun to(p2: Point2D) = path.quadTo(cp, p2)
    infix fun and(cp2: Point2D) = PathWithControlPoints(path, cp, cp2)
}

data class PathWithControlPoints(val path: Path2D, val cp1: Point2D, val cp2: Point2D) {
    infix fun to(p2: Point2D) = path.cubicTo(cp1, cp2, p2)
}
