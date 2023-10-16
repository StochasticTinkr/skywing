package com.stochastictinkr.skywing.geom

import java.awt.geom.Dimension2D

class Dimension2D(
    @get:JvmName("_width") @get:JvmSynthetic var width: Double = 0.0,
    @get:JvmName("_height") @get:JvmSynthetic var height: Double = 0.0,
) : Dimension2D() {
    override fun getWidth() = width
    override fun getHeight() = height

    override fun setSize(width: Double, height: Double) {
        this.width = width
        this.height = height
    }
}