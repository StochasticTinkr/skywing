package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.uibuilder.dsl.FontConfig
import java.awt.Font

fun buildFont(init: FontConfig.() -> Unit): Font = FontBuilder().apply(init).font

private class FontBuilder : FontConfig {
    val font get() = Font(name, style, size ?: throw IllegalStateException("Point size not specified. Use pt(size)"))
    var size: Int? = null
    var style: Int = 0
    var name: String = "Default"
        set(value) {
            if (field == "Default") field = value
            else throw IllegalStateException("Conflicting font settings selected")
        }

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
}