package com.stochastictinkr.skywing.uibuilder.dsl

import java.awt.Color
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Font
import javax.swing.JLabel

@UiBuilderDsl
interface ComponentConfig {
    fun size(dimension: Dimension)

    fun minimumSize(dimension: Dimension)
    fun maximumSize(dimension: Dimension)
    fun preferredSize(dimension: Dimension)
    fun cursor(cursor: Cursor)
    fun defaultCursor()
    fun background(color: Color)
    fun foreground(color: Color)
    fun background(init: ColorConfig.()->Unit)
    fun foreground(init: ColorConfig.()->Unit)
    fun font(font: Font)
    fun font(init: FontConfig.() -> Unit)

    fun labeledBy(label: JLabel)

}