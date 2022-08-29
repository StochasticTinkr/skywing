package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.ComponentSpec
import com.stochastictinkr.skywing.uibuilder.FontSpec
import com.stochastictinkr.skywing.uibuilder.SpecRef
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import com.stochastictinkr.skywing.uibuilder.ref
import com.stochastictinkr.skywing.uibuilder.resolveThen
import java.awt.Color
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Font

abstract class AbstractComponentBuilder<C : Component> : ComponentSpec<C>, SpecRef<C> {
    private var minimumSize: Dimension? = null
    private var maximumSize: Dimension? = null
    private var preferredSize: Dimension? = null
    private var cursor: Cursor? = null
    private var background: Color? = null
    private var foreground: Color? = null
    private var font: SpecRef<Font?>? = null
    override fun font(font: Font) {
        this.font = ref(font)
    }

    override fun font(spec: FontSpec.() -> Unit) {
        this.font = FontBuilder().apply(spec)
    }

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
        font.resolveThen(resolver, component::setFont)
    }

    override fun getConfiguredInstance(resolver: SpecResolver): C {
        val instance = getInstance(resolver)
        configure(instance, resolver)
        return instance
    }
}
