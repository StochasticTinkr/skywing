package com.stochastictinkr.skywing.awt.geom

import java.awt.geom.RectangularShape
import java.awt.geom.RoundRectangle2D

fun roundRectangle(builder: RoundRectangle2D.() -> Unit) = RoundRectangle2D.Double().apply(builder)

fun RectangularShape.asRoundRectangle() = roundRectangle { frame = this@asRoundRectangle.frame }