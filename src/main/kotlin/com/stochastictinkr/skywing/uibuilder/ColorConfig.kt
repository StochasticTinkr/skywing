package com.stochastictinkr.skywing.uibuilder

@UiBuilderDsl
interface ColorConfig {
    fun rgb(red: Int, green: Int, blue: Int, alpha: Int = 255)
    fun rgb(red: Float, green: Float, blue: Float, alpha: Float = 1.0f)
    fun rgb(red: Number, green: Number, blue: Number, alpha: Number = 1.0) =
        rgb(red.toFloat(), green.toFloat(), blue.toFloat(), alpha.toFloat())

    fun hsb(hue: Float, saturation: Float, brightness: Float, alpha: Float = 1.0f)
    fun hsb(hue: Number, saturation: Number, brightness: Number, alpha: Number = 1.0f) =
        hsb(hue.toFloat(), saturation.toFloat(), brightness.toFloat(), alpha.toFloat())
}
