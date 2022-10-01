package com.stochastictinkr.skywing.rendering

import java.awt.BasicStroke
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Shape
import java.awt.geom.Point2D
import kotlin.math.PI
import java.awt.geom.Line2D.Double as Line
import java.awt.geom.Point2D.Double as Point
import java.awt.geom.Rectangle2D.Double as Rectangle

@DslMarker
annotation class PaintDsl

enum class StrokeCap(internal val value: Int) {
    BUTT(BasicStroke.CAP_BUTT),
    ROUND(BasicStroke.CAP_ROUND),
    SQUARE(BasicStroke.CAP_SQUARE),
}

enum class StrokeJoin(internal val value: Int) {
    MITER(BasicStroke.JOIN_MITER),
    ROUND(BasicStroke.JOIN_ROUND),
    BEVEL(BasicStroke.JOIN_BEVEL),
}

@PaintDsl
data class PaintContext(val g: Graphics2D, val width: Int, val height: Int) {
    val size = width by height
    var fill = true
    var paint: Paint? by g::paint

    @PaintDsl
    interface StrokeConfig {
        var width: Float
        var endCap: StrokeCap
        var lineJoin: StrokeJoin
        var miterLimit: Float
        var dashPattern: MutableList<Float>
        var dashPhase: Float
        fun roundedCap()
        fun flatCap()
        fun squareCap()
        fun roundedJoin()
        fun beveledJoin()
        fun miteredJoin()

        fun reset()
        fun fromBasicStroke(basicStroke: BasicStroke)
    }

    data class StrokeBuilder(
        override var width: Float = 1f,
        override var endCap: StrokeCap = StrokeCap.SQUARE,
        override var lineJoin: StrokeJoin = StrokeJoin.MITER,
        override var miterLimit: Float = 10f,
        override var dashPattern: MutableList<Float> = mutableListOf(),
        override var dashPhase: Float = 0f,
    ) : StrokeConfig {
        override fun roundedCap() {
            endCap = StrokeCap.ROUND
        }

        override fun flatCap() {
            endCap = StrokeCap.BUTT
        }

        override fun squareCap() {
            endCap = StrokeCap.SQUARE
        }

        override fun roundedJoin() {
            lineJoin = StrokeJoin.ROUND
        }

        override fun beveledJoin() {
            lineJoin = StrokeJoin.BEVEL
        }

        override fun miteredJoin() {
            lineJoin = StrokeJoin.MITER
        }

        override fun reset() {
            fromBasicStroke(BasicStroke())
        }

        fun asBasicStroke() = BasicStroke(
            width,
            endCap.value,
            lineJoin.value,
            miterLimit,
            if (dashPattern.isEmpty()) null else dashPattern.toFloatArray(),
            dashPhase
        )

        override fun fromBasicStroke(basicStroke: BasicStroke) {
            this.width = basicStroke.lineWidth
            this.endCap = StrokeCap.values().first { it.value == basicStroke.endCap }
            this.lineJoin = StrokeJoin.values().first { it.value == basicStroke.lineJoin }
            this.miterLimit = basicStroke.miterLimit
            this.dashPattern = basicStroke.dashArray?.toMutableList() ?: mutableListOf()
            this.dashPhase = basicStroke.dashPhase
        }
    }

    var font: Font? by g::font
    val fontMetrics: FontMetrics? by g::fontMetrics
    fun stroke(init: StrokeConfig.() -> Unit) {
        g.stroke = StrokeBuilder().apply(init).asBasicStroke()
    }

    fun modifyStroke(init: StrokeConfig.() -> Unit) {
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

    fun draw(s: Shape) {
        g.draw(s)
    }

    fun fill(s: Shape) {
        g.fill(s)
    }

    private fun drawOrFill(s: Shape) {
        if (fill) {
            fill(s)
        } else {
            draw(s)
        }
    }

    fun line(line: Line) = g.draw(line)
    fun line(a: Point, b: Point) = line(a to b)
    fun line(x1: Number, y1: Number, x2: Number, y2: Number) = line(x1 by y1, x2 by y2)

    fun rectangle(line: Line) = drawOrFill(line.bounds2D)
    fun rectangle(rectangle: Rectangle) = drawOrFill(rectangle)
    fun rectangle(a: Point, b: Point) = drawOrFill(makeRectangle(a, b))
    fun rectangle(x1: Number, y1: Number, x2: Number, y2: Number) = drawOrFill(makeRectangle(x1, y1, x2, y2))

    fun makeRectangle(a: Point2D.Double, b: Point2D.Double) = Rectangle().apply { setFrameFromDiagonal(a, b) }

    fun makeRectangle(x1: Number, y1: Number, x2: Number, y2: Number) =
        Rectangle().apply { setFrameFromDiagonal(x1.toDouble(), y1.toDouble(), x2.toDouble(), y2.toDouble()) }

    infix fun Point.to(b: Point) = Line(this, b)

    infix fun Number.by(y: Number) = Point(this.toDouble(), y.toDouble())
    fun makePoint(x: Number, y: Number) = Point(x.toDouble(), y.toDouble())

    fun turtle(turtle: Turtle.() -> Unit) {
        val oldStroke = g.stroke
        val oldPaint = paint
        val oldTransform = g.transform
        Turtle(this).turtle()
        g.stroke = oldStroke
        g.transform = oldTransform
        paint = oldPaint
    }

    fun renderingHint(hint: Hint) {
        g.setRenderingHint(hint.key, hint.value)
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
            rendering,
            antialiasing,
            textAntialiasing,
            fractionalMetrics,
            textLcdContrast,
            interpolation,
            alphaInterpolation,
            dithering,
            colorRendering,
            strokeControl,
            resolutionVariant
        ).forEach(::renderingHint)
    }

    @PaintDsl
    class Turtle(private val context: PaintContext) {
        private var angleScale = 1.0
        private val g by context::g
        var paint by context::paint
        fun stroke(init: StrokeConfig.() -> Unit) = context.stroke(init)
        fun modifyStroke(init: StrokeConfig.() -> Unit) = context.modifyStroke(init)

        fun move(distance: Number = 1.0) {
            g.translate(0.0, distance.toDouble())
        }

        fun line(distance: Number = 1.0) {
            context.line(0, 0, 0, distance)
            g.translate(0.0, distance.toDouble())
        }

        fun degrees() {
            angleScale = PI / 180.0
        }

        fun radians() {
            angleScale = 1.0
        }

        fun turnLeft(angle: Double) {
            g.rotate(-angle * angleScale)
        }

        fun turnRight(angle: Double) {
            g.rotate(angle * angleScale)
        }
    }
}

