package com.stochastictinkr.skywing.hints

import java.awt.RenderingHints

sealed interface TextLcdContrast : Hint {
    override val key: RenderingHints.Key get() = RenderingHints.KEY_TEXT_LCD_CONTRAST

    companion object {
        fun of(value: Int): TextLcdContrast {
            require(value in 100..250) { "TextLcdContrast must be between 100 and 250, but was set to $value" }
            return TextLcdContrastValue(value)
        }

        fun default(): TextLcdContrast {
            return DefaultTextLcdContrastValue
        }
    }
}
