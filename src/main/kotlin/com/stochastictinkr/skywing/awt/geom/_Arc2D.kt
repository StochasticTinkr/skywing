package com.stochastictinkr.skywing.awt.geom

import java.awt.geom.Arc2D
import java.awt.geom.Point2D
import kotlin.math.sqrt

inline fun arc(builder: Arc2D.() -> Unit): Arc2D = Arc2D.Double().apply(builder)

var Arc2D.startAngle: Angle
    get() = Degrees(angleStart)
    set(value) {
        angleStart = value.degrees
    }

var Arc2D.extentAngle: Angle
    get() = Degrees(angleExtent)
    set(value) {
        angleExtent = value.degrees
    }

