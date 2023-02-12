package com.stochastictinkr.skywing.awt.geom

import java.awt.Dimension
import java.awt.geom.Dimension2D
import java.awt.geom.Point2D
import java.awt.geom.Rectangle2D

inline fun rectangle(init: Rectangle2D.() -> Unit) = Rectangle2D.Double().apply(init)
infix fun Point2D.rectangleTo(end: Point2D) = also { start -> rectangle { setFrameFromDiagonal(start, end) } }
fun rectangle(start: Point2D, size: Dimension2D) = rectangle { setFrame(start, size) }
fun rectangle(x1: Number, y1: Number, x2: Number, y2: Number) =
    rectangle { setFrameFromDiagonal(x1.toDouble(), y1.toDouble(), x2.toDouble(), y2.toDouble()) }

val Rectangle2D.size get() = Dimension(width.toInt(), height.toInt())