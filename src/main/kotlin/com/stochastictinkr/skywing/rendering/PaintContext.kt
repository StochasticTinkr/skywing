package com.stochastictinkr.skywing.rendering

import com.stochastictinkr.skywing.rendering.geom.by
import com.stochastictinkr.skywing.hints.Hints
import java.awt.BasicStroke
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Shape

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
    var paint: Paint? by g::paint

    val hints = Hints(g)

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

    fun draw(s: Shape) = g.draw(s)

    fun fill(s: Shape) = g.fill(s)

    fun hints(config: Hints.() -> Unit) {
        hints.config()
    }
}

