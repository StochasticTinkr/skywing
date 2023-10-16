package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.geom.Arc2D
import java.awt.geom.Dimension2D
import java.awt.geom.Ellipse2D
import java.awt.geom.Line2D
import java.awt.geom.Point2D
import java.awt.geom.Rectangle2D
import java.awt.geom.RectangularShape
import java.awt.geom.RoundRectangle2D

private val RectangularShape.aspect: Double get() = width / height

var RectangularShape.upperLeft
    get() = point(x, y)
    set(value) {
        setFrame(value, size)
    }

var RectangularShape.size
    get() = dimension(width, height)
    set(value) {
        setFrame(upperLeft, value)
    }

fun RectangularShape.setSize(width: Double, height: Double) {
    setFrame(x, y, x + width, y + height)
}

fun RectangularShape.centerAt(x: Double, y: Double, width: Double = this.width, height: Double = this.height) {
    setFrame(x - width * .5, y - height * .5, width, height)
}

fun RectangularShape.centerAt(point: Point2D, size: Dimension2D = this.size) {
    setFrame(point - size.asPoint() * .5, size)
}

inline fun RectangularShape.asRoundRectangle(init: Init<RoundRectangle2D> = {}) = frame.let {
    roundRectangle {
        frame = it
        init()
    }
}

inline fun RectangularShape.asEllipse(init: Init<Ellipse2D> = {}) = frame.let {
    ellipse {
        frame = it
        init()
    }
}

inline fun RectangularShape.asLine(init: Init<Line2D> = {}) = frame.let {
    line {
        frame = it
        init()
    }
}

inline fun RectangularShape.asRectangle(init: Init<Rectangle2D> = {}) = frame.let {
    rectangle {
        frame = it
        init()
    }
}

inline fun RectangularShape.asArc(
    startAngle: com.stochastictinkr.skywing.geom.Angle = com.stochastictinkr.skywing.geom.Degrees(0.0),
    extentAngle: com.stochastictinkr.skywing.geom.Angle = com.stochastictinkr.skywing.geom.Degrees(0.0),
    type: ArcType = ArcType.OPEN,
    init: Init<Arc2D> = {},
) = arc(upperLeft, size, startAngle, extentAngle, type, init)

fun RectangularShape.component1() = upperLeft
fun RectangularShape.component2() = size