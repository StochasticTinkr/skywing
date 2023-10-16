package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.geom.Dimension2D
import java.awt.geom.Point2D
import java.awt.geom.Rectangle2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun rectangle(init: Init<Rectangle2D> = {}): Rectangle2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return Rectangle2D.Double().apply(init)
}

fun rectangle(
    upperLeft: Point2D,
    size: Dimension2D,
): Rectangle2D {
    val (x, y) = upperLeft
    val (w, h) = size
    return Rectangle2D.Double(x, y, w, h)
}

fun Rectangle2D.copy(
    upperLeft: Point2D = this.upperLeft,
    size: Dimension2D = this.size,
) = rectangle(upperLeft, size)

infix fun Point2D.rectangleTo(end: Point2D) = let { start -> rectangle { setFrameFromDiagonal(start, end) } }
fun rectangle(x1: Number, y1: Number, x2: Number, y2: Number) =
    rectangle { setFrameFromDiagonal(x1.toDouble(), y1.toDouble(), x2.toDouble(), y2.toDouble()) }

