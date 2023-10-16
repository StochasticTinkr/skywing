package com.stochastictinkr.skywing.geom

import java.awt.geom.AffineTransform

fun interface Transformable<T : Transformable<T>> {
    fun transform(affineTransform: AffineTransform): T
}
