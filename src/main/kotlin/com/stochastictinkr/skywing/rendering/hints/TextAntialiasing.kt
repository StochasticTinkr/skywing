package com.stochastictinkr.skywing.rendering.hints

import java.awt.RenderingHints

enum class TextAntialiasing(override val value: Any) : Hint {
    ON(RenderingHints.VALUE_TEXT_ANTIALIAS_ON),
    OFF(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF),
    DEFAULT(RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT),
    GASP(RenderingHints.VALUE_TEXT_ANTIALIAS_GASP),
    LCD_HRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB),
    LCD_HBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR),
    LCD_VRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB),
    LCD_VBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR),
    ;

    override val key: RenderingHints.Key = RenderingHints.KEY_TEXT_ANTIALIASING
}