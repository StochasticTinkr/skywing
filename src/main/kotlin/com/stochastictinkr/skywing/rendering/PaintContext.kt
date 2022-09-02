package com.stochastictinkr.skywing.rendering

import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Shape
import java.awt.Stroke
import java.awt.geom.Point2D
import java.awt.geom.Line2D.Double as Line
import java.awt.geom.Point2D.Double as Point
import java.awt.geom.Rectangle2D.Double as Rectangle

data class PaintContext(val g: Graphics2D, val width: Int, val height: Int) {
    fun renderingHint(hint: Hint) {
        g.setRenderingHint(hint.key, hint.value)
    }

    fun renderingHints(vararg hints: Hint) {
        hints.forEach(::renderingHint)
    }

    fun renderingHints(
        rendering: Rendering? = null,
        antialiasing: Antialiasing? = null,
        textAntialiasing: TextAntialiasing? = null,
        fractionalMetrics: FractionalMetrics? = null,
        textLcdContrast: TextLcdContrastHint? = null,
        interpolation: Interpolation? = null,
        alphaInterpolation: AlphaInterpolation? = null,
        dithering: Dithering? = null,
        colorRendering: ColorRendering? = null,
        strokeControl: StrokeControl? = null,
        resolutionVariant: ResolutionVariant? = null,
    ) {
        listOfNotNull(
            rendering, antialiasing, textAntialiasing, fractionalMetrics, textLcdContrast, interpolation,
            alphaInterpolation,
            dithering,
            colorRendering,
            strokeControl,
            resolutionVariant
        ).forEach(::renderingHint)
    }

    var fill = true

    var paint: Paint? by g::paint
    var stroke: Stroke? by g::stroke
    var font: Font? by g::font
    val fontMetrics: FontMetrics? by g::fontMetrics

    fun clear(paint: Paint = g.background) {
        val oldPaint = this.paint
        this.paint = paint
        g.fillRect(0, 0, width, height)
        this.paint = oldPaint
    }

    fun draw(s: Shape) {
        if (fill) {
            g.fill(s)
        } else {
            g.draw(s)
        }
    }

    fun line(line: Line) = g.draw(line)
    fun line(a: Point, b: Point) = line(a to b)
    fun line(x1: Number, y1: Number, x2: Number, y2: Number) = line(makePoint(x1, y1) to makePoint(x2, y2))

    fun rectangle(line: Line) = draw(line.bounds2D)
    fun rectangle(rectangle: Rectangle) = draw(rectangle)
    fun rectangle(a: Point, b: Point) = draw(makeRectangle(a, b))
    fun rectangle(x1: Number, y1: Number, x2: Number, y2: Number) =
        draw(makeRectangle(x1, y1, x2, y2))

    fun makeRectangle(a: Point2D.Double, b: Point2D.Double) =
        Rectangle().apply { setFrameFromDiagonal(a, b) }

    fun makeRectangle(x1: Number, y1: Number, x2: Number, y2: Number) =
        Rectangle().apply { setFrameFromDiagonal(x1.toDouble(), y1.toDouble(), x2.toDouble(), y2.toDouble()) }

    infix fun Point.to(b: Point) = Line(this, b)
    fun makePoint(x: Number, y: Number) = Point(x.toDouble(), y.toDouble())
}