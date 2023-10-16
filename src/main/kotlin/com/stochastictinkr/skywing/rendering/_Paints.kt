package com.stochastictinkr.skywing.rendering

import com.stochastictinkr.skywing.utils.Init
import java.awt.Color
import java.awt.LinearGradientPaint
import java.awt.MultipleGradientPaint
import java.awt.geom.AffineTransform
import java.awt.geom.Point2D
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


fun color(r: Int, g: Int, b: Int, a: Int = 255) = Color(r, g, b, a)

operator fun Color.component1() = red
operator fun Color.component2() = green
operator fun Color.component3() = blue
operator fun Color.component4() = alpha

fun Color.copy(r: Int = red, g: Int = green, b: Int = blue, a: Int = alpha) = color(r, g, b, a)

class LinearGradientBuilder(val start: Point2D, val end: Point2D) {
    private val transform = AffineTransform()
    private var cycleMethod = MultipleGradientPaint.CycleMethod.NO_CYCLE
    private var colorSpaceType = MultipleGradientPaint.ColorSpaceType.SRGB
    private val colors = mutableListOf<Color>()
    private val fractions = mutableListOf<Float>()

    fun cycleReflect() {
        cycleMethod = MultipleGradientPaint.CycleMethod.REFLECT
    }

    fun cycleRepeat() {
        cycleMethod = MultipleGradientPaint.CycleMethod.REPEAT
    }

    fun noCycle() {
        cycleMethod = MultipleGradientPaint.CycleMethod.NO_CYCLE
    }

    fun useSRGBInterpolation() {
        colorSpaceType = MultipleGradientPaint.ColorSpaceType.SRGB
    }

    fun useLinearRGBInterpolation() {
        colorSpaceType = MultipleGradientPaint.ColorSpaceType.LINEAR_RGB
    }

    fun transform(init: Init<AffineTransform>) = transform.init()

    fun build(): LinearGradientPaint {
        return LinearGradientPaint(
            start,
            end,
            fractions.toFloatArray(),
            colors.toTypedArray(),
            cycleMethod,
            colorSpaceType,
            transform
        )
    }
}

inline fun linearGradient(start: Point2D, end: Point2D, init: Init<LinearGradientBuilder>): LinearGradientPaint {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return LinearGradientBuilder(start, end).apply(init).build()
}