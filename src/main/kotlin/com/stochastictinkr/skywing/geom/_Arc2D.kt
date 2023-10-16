package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.geom.Arc2D
import java.awt.geom.Dimension2D
import java.awt.geom.Point2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun arc(init: Init<Arc2D> = {}): Arc2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return Arc2D.Double().apply(init)
}
inline fun arc(
    upperLeft: Point2D = point(),
    size: Dimension2D = dimension(0.0, 0.0),
    startAngle: com.stochastictinkr.skywing.geom.Angle = com.stochastictinkr.skywing.geom.Degrees(0.0),
    extentAngle: com.stochastictinkr.skywing.geom.Angle = com.stochastictinkr.skywing.geom.Degrees(0.0),
    type: ArcType = ArcType.OPEN,
    init: Init<Arc2D> = {},
): Arc2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    val (x, y) = upperLeft
    val (w, h) = size
    return Arc2D.Double(x, y, w, h, startAngle.degrees, extentAngle.degrees, type.ordinal).apply(init)
}

var Arc2D.startAngle: com.stochastictinkr.skywing.geom.Angle
    get() = com.stochastictinkr.skywing.geom.Degrees(angleStart)
    set(value) {
        angleStart = value.degrees
    }

var Arc2D.extentAngle: com.stochastictinkr.skywing.geom.Angle
    get() = com.stochastictinkr.skywing.geom.Degrees(angleExtent)
    set(value) {
        angleExtent = value.degrees
    }

fun Arc2D.component3() = startAngle
fun Arc2D.component4() = extentAngle

fun Arc2D.copy(
    upperLeft: Point2D = this.upperLeft,
    size: Dimension2D = this.size,
    startAngle: com.stochastictinkr.skywing.geom.Angle = this.startAngle,
    extentAngle: com.stochastictinkr.skywing.geom.Angle = this.extentAngle,
    type: ArcType = this.type,
) = arc(upperLeft, size, startAngle, extentAngle, type)

var Arc2D.type: ArcType
    get() = ArcType.entries[arcType]
    set(value) {
        arcType = value.ordinal
    }

enum class ArcType(value: Int) {
    OPEN(Arc2D.OPEN),
    CHORD(Arc2D.CHORD),
    PIE(Arc2D.PIE),
    ;

    init {
        require(value == ordinal)
    }
}
