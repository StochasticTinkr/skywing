package com.stochastictinkr.skywing.rendering.geom

import java.awt.Dimension

infix fun Int.by(height: Int) = Dimension(this, height)

sealed interface Extent {
    operator fun invoke(extent: Int): Dimension
    operator fun invoke(dimension: Dimension): Int
}

object Width : Extent {
    override fun invoke(extent: Int) = Dimension(extent, 0)
    override fun invoke(dimension: Dimension) = dimension.width
}

object Height : Extent {
    override fun invoke(extent: Int) = Dimension(0, extent)
    override fun invoke(dimension: Dimension) = dimension.height
}

operator fun Dimension.plus(dimension: Dimension) = Dimension(width + dimension.width, height + dimension.height)
operator fun Dimension.plusAssign(dimension: Dimension) {
    width += dimension.width
    height += dimension.height
}

operator fun Dimension.times(scale: Double) = Dimension().apply { setSize(width * scale, height * scale) }
operator fun Dimension.timesAssign(scale: Double) {
    setSize(width * scale, height * scale)
}
operator fun Dimension.times(scale: Int) = Dimension().apply { setSize(width * scale, height * scale) }
operator fun Dimension.timesAssign(scale: Int) {
    setSize(width * scale, height * scale)
}

operator fun Dimension.div(scale: Double) = Dimension().apply { setSize(width / scale, height / scale) }
operator fun Dimension.divAssign(scale: Double) {
    setSize(width / scale, height / scale)
}
operator fun Dimension.div(scale: Int) = Dimension().apply { setSize(width / scale, height / scale) }
operator fun Dimension.divAssign(scale: Int) {
    setSize(width / scale, height / scale)
}

operator fun Dimension.component1() = width
operator fun Dimension.component2() = height

