package com.stochastictinkr.skywing.rendering.hints

import java.awt.Graphics2D
import java.awt.RenderingHints
import kotlin.reflect.KProperty

var Hints.rendering: Rendering by hint(Rendering.DEFAULT)
var Hints.antialiasing: Antialiasing by hint(Antialiasing.DEFAULT)
var Hints.textAntialiasing: TextAntialiasing by hint(TextAntialiasing.DEFAULT)
var Hints.fractionalMetrics: FractionalMetrics by hint(FractionalMetrics.DEFAULT)
var Hints.textLcdContrast: Int?
    get() = get(RenderingHints.KEY_TEXT_LCD_CONTRAST) as Int?
    set(value) = set(RenderingHints.KEY_TEXT_LCD_CONTRAST, value)
var Hints.interpolation: Interpolation by hint(Interpolation.NEAREST_NEIGHBOR)
var Hints.alphaInterpolation: AlphaInterpolation by hint(AlphaInterpolation.DEFAULT)
var Hints.dithering: Dithering by hint(Dithering.DEFAULT)
var Hints.colorRendering: ColorRendering by hint(ColorRendering.DEFAULT)
var Hints.strokeControl: StrokeControl by hint(StrokeControl.DEFAULT)
var Hints.resolutionVariant: ResolutionVariant by hint(ResolutionVariant.DEFAULT)


@Suppress("unused")
sealed class Hints {
    internal abstract operator fun set(key: RenderingHints.Key, value: Any?)
    internal abstract operator fun get(key: RenderingHints.Key): Any?

    operator fun Hint.invoke() = set(this)

    fun set(hint: Hint) {
        this[hint.key] = hint.value
    }

    fun renderingSpeed() = set(Rendering.SPEED)

    fun renderingQuality() = set(Rendering.QUALITY)

    fun renderingDefault() = set(Rendering.DEFAULT)

    fun antialiasingOn() = set(Antialiasing.ON)

    fun antialiasingOff() = set(Antialiasing.OFF)

    fun antialiasingDefault() = set(Antialiasing.DEFAULT)

    fun textAntialiasingOn() = set(TextAntialiasing.ON)

    fun textAntialiasingOff() = set(TextAntialiasing.OFF)

    fun textAntialiasingDefault() = set(TextAntialiasing.DEFAULT)

    fun textAntialiasingGasp() = set(TextAntialiasing.GASP)

    fun textAntialiasingLcdHrgb() = set(TextAntialiasing.LCD_HRGB)

    fun textAntialiasingLcdHbgr() = set(TextAntialiasing.LCD_HBGR)

    fun textAntialiasingLcdVrgb() = set(TextAntialiasing.LCD_VRGB)

    fun textAntialiasingLcdVbgr() = set(TextAntialiasing.LCD_VBGR)

    fun fractionalMetricsOn() = set(FractionalMetrics.ON)

    fun fractionalMetricsOff() = set(FractionalMetrics.OFF)

    fun fractionalMetricsDefault() = set(FractionalMetrics.DEFAULT)

    fun textLcdContrast(value: Int) {
        textLcdContrast = value
    }

    fun interpolationNearestNeighbor() = set(Interpolation.NEAREST_NEIGHBOR)

    fun interpolationBilinear() = set(Interpolation.BILINEAR)

    fun interpolationBicubic() = set(Interpolation.BICUBIC)

    fun alphaInterpolationSpeed() = set(AlphaInterpolation.SPEED)

    fun alphaInterpolationQuality() = set(AlphaInterpolation.QUALITY)

    fun alphaInterpolationDefault() = set(AlphaInterpolation.DEFAULT)

    fun ditheringEnable() = set(Dithering.ENABLE)

    fun ditheringDisable() = set(Dithering.DISABLE)

    fun ditheringDefault() = set(Dithering.DEFAULT)

    fun colorRenderingSpeed() = set(ColorRendering.SPEED)

    fun colorRenderingQuality() = set(ColorRendering.QUALITY)

    fun colorRenderingDefault() = set(ColorRendering.DEFAULT)

    fun strokeControlNormalize() = set(StrokeControl.NORMALIZE)

    fun strokeControlPure() = set(StrokeControl.PURE)

    fun strokeControlDefault() = set(StrokeControl.DEFAULT)

    fun resolutionVariantDefault() = set(ResolutionVariant.DEFAULT)

    fun resolutionVariantBase() = set(ResolutionVariant.BASE)

    fun resolutionVariantSizeFit() = set(ResolutionVariant.SIZE_FIT)

    fun resolutionVariantDpiFit() = set(ResolutionVariant.DPI_FIT)
}


private class OutOf<H : Hint>(items: Array<H>, val defaultValue: H) {
    val lookup = items.associateBy { it.value }
    operator fun getValue(hints: Hints, property: KProperty<*>): H =
        lookup.getOrDefault(hints[defaultValue.key], defaultValue)

    operator fun setValue(hints: Hints, property: KProperty<*>, hint: H) = hints.set(hint.key, hint.value)
}

private inline fun <reified H> hint(defaultValue: H): OutOf<H> where H : Hint, H : Enum<H> {
    return OutOf(H::class.java.enumConstants, defaultValue)
}


private class GraphicsHints(private val g: Graphics2D) : Hints() {
    override fun set(key: RenderingHints.Key, value: Any?) {
        g.setRenderingHint(key, value)
    }

    override fun get(key: RenderingHints.Key): Any? = g.getRenderingHint(key)
}

private class RenderingHintHints(private val hints: RenderingHints) : Hints() {
    override fun set(key: RenderingHints.Key, value: Any?) {
        hints[key] = value
    }

    override fun get(key: RenderingHints.Key): Any? = hints[key]
}

operator fun Hints.invoke(hint: Hint) = set(hint)

fun Hints(g: Graphics2D): Hints = GraphicsHints(g)
fun Hints(hints: RenderingHints): Hints = RenderingHintHints(hints)