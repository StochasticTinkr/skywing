package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.geom.Dimension2D
import java.awt.geom.Point2D
import java.awt.geom.RoundRectangle2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun roundRectangle(init: Init<RoundRectangle2D> = {}): RoundRectangle2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return RoundRectangle2D.Double().apply(init)
}

fun roundRectangle(
    x: Double = 0.0,
    y: Double = 0.0,
    width: Double = 0.0,
    height: Double = 0.0,
    arcWidth: Double = 0.0,
    arcHeight: Double = 0.0,
) = RoundRectangle2D.Double(x, y, width, height, arcWidth, arcHeight)

fun roundRectangle(
    upperLeft: Point2D = point(),
    size: Dimension2D = dimension(),
    arcSize: Dimension2D = dimension(),
): RoundRectangle2D.Double {
    val (x, y) = upperLeft
    val (width, height) = size
    val (arcWidth, arcHeight) = arcSize
    return roundRectangle(x, y, width, height, arcWidth, arcHeight)
}

fun RoundRectangle2D.copy(
    x: Double = this.x,
    y: Double = this.y,
    width: Double = this.width,
    height: Double = this.height,
    arcWidth: Double = this.arcWidth,
    arcHeight: Double = this.arcHeight,
) = roundRectangle(x, y, width, height, arcWidth, arcHeight)

var RoundRectangle2D.arcSize
    get() = dimension(arcWidth, arcHeight)
    set(value) {
        val (arcWidth, arcHeight) = value
        setRoundRect(x, y, width, height, arcWidth, arcHeight)
    }

fun RoundRectangle2D.component3() = arcSize