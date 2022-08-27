@file:Suppress("unused")

package com.stoachstictinkr.skywing

import java.awt.Container
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Paint
import java.awt.RenderingHints
import java.awt.Shape
import java.awt.image.BufferedImage
import javax.swing.JComponent

import java.awt.geom.Line2D.Double as Line
import java.awt.geom.Point2D.Double as Point

sealed interface Hint {
    val key: RenderingHints.Key
    val value: Any?
}

enum class Antialiasing(override val value: Any) : Hint {
    ON(RenderingHints.VALUE_ANTIALIAS_ON),
    OFF(RenderingHints.VALUE_ANTIALIAS_OFF),
    DEFAULT(RenderingHints.VALUE_ANTIALIAS_DEFAULT)
    ;

    override val key = RenderingHints.KEY_ANTIALIASING
}

enum class Rendering(override val value: Any) : Hint {
    SPEED(RenderingHints.VALUE_RENDER_SPEED),
    QUALITY(RenderingHints.VALUE_RENDER_QUALITY),
    DEFAULT(RenderingHints.VALUE_RENDER_DEFAULT),
    ;

    override val key = RenderingHints.KEY_RENDERING
}

enum class Dithering(override val value: Any) : Hint {
    ENABLE(RenderingHints.VALUE_DITHER_ENABLE),
    DISABLE(RenderingHints.VALUE_DITHER_DISABLE),
    DEFAULT(RenderingHints.VALUE_DITHER_DEFAULT),
    ;

    override val key = RenderingHints.KEY_DITHERING
}

enum class TextAntialiasing(override val value: Any) : Hint {
    ON(RenderingHints.VALUE_TEXT_ANTIALIAS_ON),
    OFF(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF),
    DEFAULT(RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT),
    GASP(RenderingHints.VALUE_TEXT_ANTIALIAS_GASP),
    LCD_HRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB),
    LCD_HBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR),
    LCD_VRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB),
    LCD_VBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR),
    ;

    override val key = RenderingHints.KEY_TEXT_ANTIALIASING
}

class TextLcdContrast private constructor(n: Unit, override val value: Int?) : Hint {
    constructor(value: Int) : this(
        Unit, if (value in 100..250) value else {
            throw IllegalArgumentException("TextLcdContrast must be between 100 and 250")
        }
    )

    companion object {
        @JvmStatic
        val DEFAULT = TextLcdContrast(Unit, null)
    }

    override val key = RenderingHints.KEY_TEXT_LCD_CONTRAST
}

enum class FractionalMetrics(override val value: Any) : Hint {
    ON(RenderingHints.VALUE_FRACTIONALMETRICS_ON),
    OFF(RenderingHints.VALUE_FRACTIONALMETRICS_OFF),
    DEFAULT(RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT),
    ;

    override val key = RenderingHints.KEY_FRACTIONALMETRICS
}

enum class Interpolation(override val value: Any) : Hint {
    NEAREST_NEIGHBOR(RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR),
    BILINEAR(RenderingHints.VALUE_INTERPOLATION_BILINEAR),
    BICUBIC(RenderingHints.VALUE_INTERPOLATION_BICUBIC),
    ;

    override val key = RenderingHints.KEY_INTERPOLATION
}

enum class AlphaInterpolation(override val value: Any) : Hint {
    SPEED(RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED),
    QUALITY(RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY),
    DEFAULT(RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT),
    ;

    override val key = RenderingHints.KEY_ALPHA_INTERPOLATION
}

enum class ColorRendering(override val value: Any) : Hint {
    SPEED(RenderingHints.VALUE_COLOR_RENDER_SPEED),
    QUALITY(RenderingHints.VALUE_COLOR_RENDER_QUALITY),
    DEFAULT(RenderingHints.VALUE_COLOR_RENDER_DEFAULT),
    ;

    override val key = RenderingHints.KEY_COLOR_RENDERING
}

enum class StrokeControl(override val value: Any) : Hint {
    NORMALIZE(RenderingHints.VALUE_STROKE_NORMALIZE),
    PURE(RenderingHints.VALUE_STROKE_PURE),
    DEFAULT(RenderingHints.VALUE_STROKE_DEFAULT),
    ;

    override val key = RenderingHints.KEY_STROKE_CONTROL
}

enum class ResolutionVariant(override val value: Any) : Hint {
    DEFAULT(RenderingHints.VALUE_RESOLUTION_VARIANT_DEFAULT),
    BASE(RenderingHints.VALUE_RESOLUTION_VARIANT_BASE),
    SIZE_FIT(RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT),
    DPI_FIT(RenderingHints.VALUE_RESOLUTION_VARIANT_DPI_FIT),
    ;

    override val key = RenderingHints.KEY_RESOLUTION_VARIANT
}

data class PaintContext(val g: Graphics2D, val width: Int, val height: Int) {
    fun renderingHint(hint: Hint) {
        g.setRenderingHint(hint.key, hint.value)
    }

    fun renderingHints(vararg hints: Hint) {
        hints.forEach {
            renderingHint(it)
        }
    }

    var paint by g::paint
    var stroke by g::stroke
    var font by g::font
    val fontMetrics by g::fontMetrics

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

    infix fun Point.to(b: Point) = Line(this, b)
}

class CustomComponent(
    var painter: PaintContext.() -> Unit = {},
) : JComponent() {
    fun painter(painter: PaintContext.() -> Unit) {
        this.painter = painter
    }

    override fun paintComponent(g: Graphics) {
        withPaintContext(g)
    }

    private fun withPaintContext(g: Graphics) {
        if (g is Graphics2D) {
            paintAndDispose(g.create() as Graphics2D)
        } else {
            BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).also { img ->
                paintAndDispose(img.createGraphics())
                g.drawImage(img, 0, 0, null)
            }
        }
    }

    private fun paintAndDispose(g: Graphics2D) {
        try {
            painter(PaintContext(g, width, height))
        } finally {
            g.dispose()
        }
    }
}

fun Container.customComponent(init: CustomComponent.() -> Unit = {}) =
    maybeAdd(CustomComponent().apply(init))

