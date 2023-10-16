package com.stochastictinkr.skywing.geom

import java.awt.Dimension
import java.awt.geom.Dimension2D

sealed interface Extent {
    operator fun invoke(extent: Int): Dimension
    operator fun invoke(dimension: Dimension): Int
    operator fun invoke(extent: Double): Dimension2D
    operator fun invoke(dimension: Dimension2D): Double
}

data object Height : Extent {
    override fun invoke(extent: Int) = Dimension(0, extent)
    override fun invoke(dimension: Dimension) = dimension.height
    override fun invoke(extent: Double) = Dimension2D(0.0, extent)
    override fun invoke(dimension: Dimension2D) = dimension.height
}

data object Width : Extent {
    override fun invoke(extent: Int) = Dimension(extent, 0)
    override fun invoke(dimension: Dimension) = dimension.width
    override fun invoke(extent: Double) = Dimension2D(extent, 0.0)
    override fun invoke(dimension: Dimension2D) = dimension.width
}