package com.stochastictinkr.skywing.awt.geom

import java.awt.geom.Ellipse2D
import java.awt.geom.RectangularShape

fun ellipse(builder: Ellipse2D.() -> Unit) = Ellipse2D.Double().apply(builder)
fun RectangularShape.asEllipse() = ellipse { frame = this@asEllipse.frame }