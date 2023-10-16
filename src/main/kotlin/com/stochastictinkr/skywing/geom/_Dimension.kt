package com.stochastictinkr.skywing.geom

import java.awt.Dimension
import java.awt.geom.Dimension2D as AwtDimension2D

fun dimension(width: Int, height: Int) = Dimension(width, height)
fun dimension(width: Double, height: Double) = Dimension2D(width, height)
fun dimension() = Dimension2D()

infix fun Int.by(height: Int) = Dimension(this, height)
infix fun Double.by(height: Double) = Dimension2D(this, height)

fun Dimension.copy(width: Int = this.width, height: Int = this.height) = dimension(width, height)
fun AwtDimension2D.copy(width: Double = this.width, height: Double = this.height) = dimension(width, height)

operator fun Dimension.plus(that: Dimension) = dimension(width + that.width, height + that.height)
operator fun AwtDimension2D.plus(that: AwtDimension2D) = dimension(width + that.width, height + that.height)

operator fun Dimension.plusAssign(that: Dimension) = setSize(width + that.width, height + that.height)
operator fun AwtDimension2D.plusAssign(that: AwtDimension2D) = setSize(width + that.width, height + that.height)

operator fun Dimension.minus(that: Dimension) = dimension(width - that.width, height - that.height)
operator fun AwtDimension2D.minus(that: AwtDimension2D) = dimension(width - that.width, height - that.height)

operator fun Dimension.minusAssign(that: Dimension) = setSize(width - that.width, height - that.height)
operator fun AwtDimension2D.minusAssign(that: AwtDimension2D) = setSize(width - that.width, height - that.height)

operator fun Dimension.times(scale: Int) = dimension(width * scale, height * scale)
operator fun AwtDimension2D.times(scale: Double) = dimension(width * scale, height * scale)

operator fun Dimension.timesAssign(scale: Int) = setSize(width * scale, height * scale)
operator fun AwtDimension2D.timesAssign(scale: Double) = setSize(width * scale, height * scale)

operator fun Int.times(dimension: Dimension) = dimension * this
operator fun Double.times(dimension: AwtDimension2D) = dimension * this

operator fun Dimension.div(scale: Int) = dimension(width / scale, height / scale)
operator fun AwtDimension2D.div(scale: Double) = dimension(width / scale, height / scale)

operator fun Dimension.divAssign(scale: Int) = setSize(width / scale, height / scale)
operator fun AwtDimension2D.divAssign(scale: Double) = setSize(width / scale, height / scale)

operator fun Dimension.div(that: Dimension) = dimension(width / that.width, height / that.height)
operator fun AwtDimension2D.div(scale: AwtDimension2D) = dimension(width / scale.width, height / scale.height)

operator fun Dimension.component1() = width
operator fun AwtDimension2D.component1() = width

operator fun Dimension.component2() = height
operator fun AwtDimension2D.component2() = height

fun Dimension.asPoint() = point(width, height)
fun AwtDimension2D.asPoint() = point(width, height)