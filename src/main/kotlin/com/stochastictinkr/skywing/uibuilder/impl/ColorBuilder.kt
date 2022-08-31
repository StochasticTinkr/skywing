package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.ColorConfig
import com.stochastictinkr.skywing.utils.clampedTo
import java.awt.Color
import kotlin.math.roundToInt

internal fun buildColor(init: ColorConfig.() -> Unit): Color =
    buildOptionalColor(init) ?: throw IllegalStateException("No color selected")

internal fun buildOptionalColor(init: ColorConfig.() -> Unit): Color? =
    ColorBuilder().apply(init).color

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

