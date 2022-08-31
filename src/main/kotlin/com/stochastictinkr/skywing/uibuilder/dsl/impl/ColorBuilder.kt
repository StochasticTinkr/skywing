package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.uibuilder.dsl.ColorConfig
import java.awt.Color
import kotlin.math.roundToInt

fun buildColor(init: ColorConfig.() -> Unit): Color =
    buildOptionalColor(init) ?: throw IllegalStateException("No color selected")

fun buildOptionalColor(init: ColorConfig.() -> Unit): Color? =
    ColorBuilder().apply(init).color

private infix fun Float.clampedTo(range: ClosedFloatingPointRange<Float>): Float {
    if (range.contains(this)) {
        return this
    }
    if (range.start > this) {
        return range.start
    } else return range.endInclusive
}

private class ColorBuilder : ColorConfig {
    var color: Color? = null
        set(value) = if (field == null) field = value
        else throw IllegalStateException("Multiple colors selected for a single color field")

    override fun rgb(red: Int, green: Int, blue: Int, alpha: Int) {
        color = Color(red, green, blue, alpha)
    }

    override fun rgb(red: Float, green: Float, blue: Float, alpha: Float) {
        color = Color(red, green, blue, alpha)
    }

    override fun hsb(hue: Float, saturation: Float, brightness: Float, alpha: Float) {
        val rgbInt = Color.HSBtoRGB(hue, saturation, brightness)
        val alphaInt = (alpha.clampedTo(0.0f..1.0f) * 255).roundToInt()
        color = Color(rgbInt.or(alphaInt.shl(24)))
    }
}

