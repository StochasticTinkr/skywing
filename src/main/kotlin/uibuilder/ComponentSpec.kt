package com.stochastictinkr.skywing.uibuilder

import java.awt.Color
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Font

@UiBuilderDsl
interface ComponentSpec<C : Component> {
    fun minimumSize(dimension: Dimension)
    fun maximumSize(dimension: Dimension)
    fun preferredSize(dimension: Dimension)
    fun cursor(cursor: Cursor)
    fun defaultCursor()
    fun background(color: Color)
    fun foreground(color: Color)
    fun font(font: Font)
    fun font(spec: FontSpec.() -> Unit)
    fun configure(component: C, resolver: SpecResolver)
}