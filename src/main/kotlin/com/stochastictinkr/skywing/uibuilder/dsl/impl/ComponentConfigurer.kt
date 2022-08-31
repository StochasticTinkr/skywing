package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.uibuilder.dsl.ColorConfig
import com.stochastictinkr.skywing.uibuilder.dsl.ComponentConfig
import com.stochastictinkr.skywing.uibuilder.dsl.FontConfig
import java.awt.Color
import java.awt.Component
import java.awt.Cursor
import java.awt.Dimension
import java.awt.Font
import javax.swing.JLabel

fun componentConfigurer(): Configurer<ComponentConfig, Component> =
    ComponentConfigurer().asConfigurer { component ->
        size?.let { component.size = it }
        component.minimumSize = minimumSize
        component.maximumSize = maximumSize
        component.preferredSize = preferredSize
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        component.cursor = cursor
        component.background = background
        component.foreground = foreground
        component.font = font
        labeledBy?.labelFor = component
    }

private class ComponentConfigurer : ComponentConfig {
    var size: Dimension? = null
    var minimumSize: Dimension? = null
    var maximumSize: Dimension? = null
    var preferredSize: Dimension? = null
    var cursor: Cursor? = null
    var background: Color? = null
    var foreground: Color? = null
    var font: Font? = null
    var labeledBy: JLabel? = null

    override fun labeledBy(label: JLabel) {
        labeledBy = label
    }

    override fun size(dimension: Dimension) {
        size = dimension
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

    override fun defaultCursor() {}

    override fun background(color: Color) {
        background = color
    }

    override fun background(init: ColorConfig.() -> Unit) {
        background(buildColor(init))
    }

    override fun foreground(color: Color) {
        foreground = color
    }

    override fun foreground(init: ColorConfig.() -> Unit) {
        foreground(buildColor(init))
    }

    override fun font(font: Font) {
        this.font = font
    }

    override fun font(init: FontConfig.() -> Unit): Unit = font(buildFont(init))

}