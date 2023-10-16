package com.stochastictinkr.skywing.rendering

import com.stochastictinkr.skywing.geom.Angle
import com.stochastictinkr.skywing.rendering.hints.Hints
import com.stochastictinkr.skywing.utils.Init
import java.awt.BasicStroke
import java.awt.Font
import java.awt.Graphics2D
import java.awt.geom.Point2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun Graphics2D.hints(init: Init<Hints>) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    hints.init()
}

val Graphics2D.hints get() = Hints(this)

fun Graphics2D.rotateAround(angle: Angle, point: Point2D) =
    rotate(angle.radians, point.x, point.y)

fun Graphics2D.rotate(angle: Angle) = rotate(angle.radians)
fun Graphics2D.rotate(angle: Angle, tx: Double, ty: Double) =
    rotate(angle.radians, tx, ty)

fun Graphics2D.translate(vect: Point2D) = translate(vect.x, vect.y)
fun Graphics2D.fontMetrics(font: Font = getFont()) = getFontMetrics(font)

inline fun Graphics2D.stroke(init: StrokeBuilder.() -> Unit) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    stroke = StrokeBuilder().apply(init).asBasicStroke()
}

fun Graphics2D.modifyStroke(init: StrokeBuilder.() -> Unit) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    stroke = StrokeBuilder().let {
        it.fromBasicStroke(stroke as? BasicStroke ?: BasicStroke())
        it.init()
        it.asBasicStroke()
    }
}

fun Graphics2D.stroke(
    width: Float = 1f,
    endCap: StrokeCap = StrokeCap.SQUARE,
    lineJoin: StrokeJoin = StrokeJoin.MITER,
    miterLimit: Float = 10f,
    dashPattern: MutableList<Float> = mutableListOf(),
    dashPhase: Float = 0f,
) {
    stroke = StrokeBuilder(width, endCap, lineJoin, miterLimit, dashPattern, dashPhase).asBasicStroke()
}


inline fun <R> Graphics2D.use(block: (Graphics2D) -> R): R {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return try {
        block(this)
    } finally {
        dispose()
    }
}

