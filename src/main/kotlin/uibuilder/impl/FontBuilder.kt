package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.FontSpec
import com.stochastictinkr.skywing.uibuilder.SpecRef
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import java.awt.Font

class FontBuilder : FontSpec, SpecRef<Font> {
    private var name: String? = null
    private var style: Int = Font.PLAIN
    private var size: Int = 8

    override fun dialog() {
        name = Font.DIALOG
    }

    override fun dialogInput() {
        name = Font.DIALOG_INPUT
    }

    override fun sansSerif() {
        name = Font.SANS_SERIF
    }

    override fun serif() {
        name = Font.SERIF
    }

    override fun monospaced() {
        name = Font.MONOSPACED
    }

    override fun bold() {
        style = style or Font.BOLD
    }

    override fun italic() {
        style = style or Font.ITALIC
    }

    override fun pt(size: Int) {
        this.size = size
    }

    override fun getInstance(resolver: SpecResolver) = Font(name, style, size)
}