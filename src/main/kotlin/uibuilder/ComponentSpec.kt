package com.stoachstictinkr.skywing.uibuilder

import com.stoachstictinkr.skywing.UiBuilderDsl
import java.awt.Color
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension

@UiBuilderDsl
interface ComponentSpec<C : Component> : SpecRef<C> {
    fun minimumSize(dimension: Dimension)
    fun maximumSize(dimension: Dimension)
    fun preferredSize(dimension: Dimension)
    fun cursor(cursor: Cursor)
    fun defaultCursor()
    fun background(color: Color)
    fun foreground(color: Color)
    fun configure(component: C, resolver: SpecResolver)
}