package com.stoachstictinkr.skywing.rendering

import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Shape
import java.awt.Stroke
import java.awt.geom.Line2D
import java.awt.geom.Point2D

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

    var paint: Paint? by g::paint
    var stroke: Stroke? by g::stroke
    var font: Font? by g::font
    val fontMetrics: FontMetrics? by g::fontMetrics

    fun clear(paint: Paint) {
        val oldPaint = this.paint
        this.paint = paint
        g.fillRect(0, 0, width, height)
        this.paint = oldPaint
    }

    fun draw(s: Shape) {
        g.draw(s)
    }

    fun fill(s: Shape) {
        g.fill(s)
    }

    infix fun Point2D.Double.to(b: Point2D.Double) = Line2D.Double(this, b)
}