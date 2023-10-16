package com.stochastictinkr.skywing.geom

import com.stochastictinkr.skywing.utils.Init
import java.awt.geom.Dimension2D
import java.awt.geom.Ellipse2D
import java.awt.geom.Point2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun ellipse(init: Init<Ellipse2D.Double> = {}): Ellipse2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return Ellipse2D.Double().apply(init)
}

inline fun ellipse(
    upperLeft: Point2D = point(),
    size: Dimension2D = dimension(),
    init: Init<Ellipse2D.Double> = {},
): Ellipse2D.Double {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    val (x, y) = upperLeft
    val (w, h) = size
    return Ellipse2D.Double(x, y, w, h).apply(init)
}

inline fun circle(center: Point2D, radius: Double, init: Init<Ellipse2D.Double> = {}): Ellipse2D {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    val size = radius + radius
    return ellipse(center - point(radius, radius), dimension(size, size), init)
}
