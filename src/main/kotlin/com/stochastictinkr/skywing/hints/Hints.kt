package com.stochastictinkr.skywing.hints

import java.awt.Graphics2D
import java.awt.RenderingHints
import kotlin.reflect.KProperty

@Suppress("unused")
class Hints(
    private val setHint: (RenderingHints.Key, Any?) -> Unit,
    private val getHint: (RenderingHints.Key) -> Any?,
) {
    constructor(g: Graphics2D) : this(g::setRenderingHint, g::getRenderingHint)
    constructor(renderingHints: RenderingHints) : this(renderingHints::put, renderingHints::get)

    var rendering: Rendering by hint(Rendering.DEFAULT)
    var antialiasing: Antialiasing by hint(Antialiasing.DEFAULT)
    var textAntialiasing: TextAntialiasing by hint(TextAntialiasing.DEFAULT)
    var fractionalMetrics: FractionalMetrics by hint(FractionalMetrics.DEFAULT)
    var textLcdContrast: TextLcdContrast
        get() = findLcdContrast()
        set(value) = setHint(value.key, value.value)
    var interpolation: Interpolation by hint(Interpolation.NEAREST_NEIGHBOR)
    var alphaInterpolation: AlphaInterpolation by hint(AlphaInterpolation.DEFAULT)
    var dithering: Dithering by hint(Dithering.DEFAULT)
    var colorRendering: ColorRendering by hint(ColorRendering.DEFAULT)
    var strokeControl: StrokeControl by hint(StrokeControl.DEFAULT)
    var resolutionVariant: ResolutionVariant by hint(ResolutionVariant.DEFAULT)

    fun renderingSpeed() {
        rendering = Rendering.SPEED
    }

    fun renderingQuality() {
        rendering = Rendering.QUALITY
    }

    fun renderingDefault() {
        rendering = Rendering.DEFAULT
    }

    fun antialiasingOn() {
        antialiasing = Antialiasing.ON
    }

    fun antialiasingOf() {
        antialiasing = Antialiasing.OFF
    }

    fun antialiasingDefault() {
        antialiasing = Antialiasing.DEFAULT
    }

    fun textAntialiasingOn() {
        textAntialiasing = TextAntialiasing.ON
    }

    fun textAntialiasingOff() {
        textAntialiasing = TextAntialiasing.OFF
    }

    fun textAntialiasingDefault() {
        textAntialiasing = TextAntialiasing.DEFAULT
    }

    fun textAntialiasingGasp() {
        textAntialiasing = TextAntialiasing.GASP
    }

    fun textAntialiasingLcdHrgb() {
        textAntialiasing = TextAntialiasing.LCD_HRGB
    }

    fun textAntialiasingLcdHbgr() {
        textAntialiasing = TextAntialiasing.LCD_HBGR
    }

    fun textAntialiasingLcdVrgb() {
        textAntialiasing = TextAntialiasing.LCD_VRGB
    }

    fun textAntialiasingLcdVbgr() {
        textAntialiasing = TextAntialiasing.LCD_VBGR
    }

    fun fractionalMetricsOn() {
        fractionalMetrics = FractionalMetrics.ON
    }

    fun fractionalMetricsOff() {
        fractionalMetrics = FractionalMetrics.OFF
    }

    fun fractionalMetricsDefault() {
        fractionalMetrics = FractionalMetrics.DEFAULT
    }

    fun textLcdContrast(value: Int) {
        textLcdContrast = TextLcdContrast.of(value)
    }

    fun defaultTextLcdContrast() {
        textLcdContrast = TextLcdContrast.default()
    }

    fun interpolationNearestNeighbor() {
        interpolation = Interpolation.NEAREST_NEIGHBOR
    }

    fun interpolationBilinear() {
        interpolation = Interpolation.BILINEAR
    }

    fun interpolationBicubic() {
        interpolation = Interpolation.BICUBIC
    }

    fun alphaInterpolationSpeed() {
        alphaInterpolation = AlphaInterpolation.SPEED
    }

    fun alphaInterpolationQuality() {
        alphaInterpolation = AlphaInterpolation.QUALITY
    }

    fun alphaInterpolationDefault() {
        alphaInterpolation = AlphaInterpolation.DEFAULT
    }

    fun ditheringEnable() {
        dithering = Dithering.ENABLE
    }

    fun ditheringDisable() {
        dithering = Dithering.DISABLE
    }

    fun ditheringDefault() {
        dithering = Dithering.DEFAULT
    }

    fun colorRenderingSpeed() {
        colorRendering = ColorRendering.SPEED
    }

    fun colorRenderingQuality() {
        colorRendering = ColorRendering.QUALITY
    }

    fun colorRenderingDefault() {
        colorRendering = ColorRendering.DEFAULT
    }

    fun strokeControlNormalize() {
        strokeControl = StrokeControl.NORMALIZE
    }

    fun strokeControlPure() {
        strokeControl = StrokeControl.PURE
    }

    fun strokeControlDefault() {
        strokeControl = StrokeControl.DEFAULT
    }

    fun resolutionVariantDefault() {
        resolutionVariant = ResolutionVariant.DEFAULT
    }

    fun resolutionVariantBase() {
        resolutionVariant = ResolutionVariant.BASE
    }

    fun resolutionVariantSizeFit() {
        resolutionVariant = ResolutionVariant.SIZE_FIT
    }

    fun resolutionVariantDpiFit() {
        resolutionVariant = ResolutionVariant.DPI_FIT
    }

    private fun findLcdContrast(): TextLcdContrast {
        return (getHint(RenderingHints.KEY_TEXT_LCD_CONTRAST) as? Int)?.let(TextLcdContrast.Companion::of)
            ?: TextLcdContrast.default()
    }

    private inner class OutOf<H : Hint>(val items: Array<H>, val defaultValue: H) {
        fun get(): H = items.find { hint -> getHint(hint.key) == hint.value } ?: defaultValue
        fun set(hint: H) = setHint(hint.key, hint.value)
        operator fun getValue(hints: Hints, property: KProperty<*>): H = get()
        operator fun setValue(hints: Hints, property: KProperty<*>, hint: H) = set(hint)
    }

    private inline fun <reified H> hint(defaultValue: H): OutOf<H> where H : Hint, H : Enum<H> {
        return OutOf(H::class.java.enumConstants, defaultValue)
    }
}

