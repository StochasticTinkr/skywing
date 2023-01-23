package com.stochastictinkr.skywing.rendering

import com.stochastictinkr.skywing.awt.geom.Angle
import com.stochastictinkr.skywing.hints.Hints
import com.stochastictinkr.skywing.rendering.geom.by
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Rectangle
import java.awt.Shape
import java.awt.geom.Point2D

@DslMarker
annotation class PaintDsl

data class PaintContext(val g: Graphics2D, val width: Int, val height: Int) {
    val size = width by height
    var paint: Paint? by g::paint
    var color: Color? by g::color
    val hints = Hints(g)
    val clipBounds: Rectangle by g::clipBounds

    var font: Font? by g::font
    val fontMetrics: FontMetrics? by g::fontMetrics

    fun fontMetrics(font: Font) = g.getFontMetrics(font)

    fun stroke(init: StrokeBuilder.() -> Unit) {
        g.stroke = StrokeBuilder().apply(init).asBasicStroke()
    }

    fun modifyStroke(init: StrokeBuilder.() -> Unit) {
        g.stroke = StrokeBuilder().let {
            it.fromBasicStroke(g.stroke as? BasicStroke ?: BasicStroke())
            it.init()
            it.asBasicStroke()
        }
    }

    fun stroke(
        width: Float = 1f,
        endCap: StrokeCap = StrokeCap.SQUARE,
        lineJoin: StrokeJoin = StrokeJoin.MITER,
        miterLimit: Float = 10f,
        dashPattern: MutableList<Float> = mutableListOf(),
        dashPhase: Float = 0f,
    ) {
        g.stroke = StrokeBuilder(width, endCap, lineJoin, miterLimit, dashPattern, dashPhase).asBasicStroke()
    }

    fun clear(paint: Paint = g.background) {
        val oldPaint = this.paint
        this.paint = paint
        g.fillRect(0, 0, width, height)
        this.paint = oldPaint
    }

    fun draw(s: Shape) = g.draw(s)

    fun fill(s: Shape) = g.fill(s)

    fun hints(config: Hints.() -> Unit) = hints.config()

    fun translate(tx: Double, ty: Double) = g.translate(tx, ty)
    fun translate(vect: Point2D) = g.translate(vect.x, vect.y)
    fun rotate(angle: Angle) = g.rotate(angle.radians)
    infix fun Angle.rotateAround(point: Point2D) = g.rotate(radians, point.x, point.y)
    fun rotate(angle: Angle, point: Point2D) = angle rotateAround point
    fun rotate(angle: Angle, tx: Double, ty: Double) = g.rotate(angle.radians, tx, ty)
    fun scale(sx: Double, sy: Double) = g.scale(sx, sy)
    fun shear(shx: Double, shy: Double) = g.shear(shx, shy)
}

