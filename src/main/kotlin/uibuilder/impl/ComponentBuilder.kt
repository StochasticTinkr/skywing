package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.uibuilder.ComponentSpec
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import java.awt.Color
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension

abstract class ComponentBuilder<C : Component> : ComponentSpec<C> {
    private var minimumSize: Dimension? = null
    private var maximumSize: Dimension? = null
    private var preferredSize: Dimension? = null
    private var cursor: Cursor? = null
    private var background: Color? = null
    private var foreground: Color? = null
    override fun minimumSize(dimension: Dimension) {
        minimumSize = dimension
    }

    override fun maximumSize(dimension: Dimension) {
        maximumSize = dimension
    }

    override fun preferredSize(dimension: Dimension) {
        preferredSize = dimension
    }

    override fun cursor(cursor: Cursor) {
        this.cursor = cursor
    }

    override fun defaultCursor() {
        this.cursor = null
    }

    override fun background(color: Color) {
        background = color
    }

    override fun foreground(color: Color) {
        foreground = color
    }

    override fun configure(component: C, resolver: SpecResolver) {
        component.minimumSize = minimumSize
        component.maximumSize = maximumSize
        component.preferredSize = preferredSize
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        component.cursor = cursor
        if (background != null) component.background = background
        if (foreground != null) component.foreground = foreground
    }

    override fun getConfiguredInstance(resolver: SpecResolver): C {
        val instance = getInstance(resolver)
        configure(instance, resolver)
        return instance
    }
}
