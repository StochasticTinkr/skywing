package com.stochastictinkr.skywing.uibuilder

import java.awt.Component
import javax.swing.Icon
import javax.swing.JLabel

interface JLabelSpec : JComponentSpec<JLabel> {
    fun text(text: String)
    fun icon(icon: Icon)
    fun icon(builder: IconSpec.() -> Icon)
    fun disabledIcon(icon: Icon)
    fun disabledIcon(builder: IconSpec.() -> Icon)
    fun labelFor(component: SpecRef<out Component>)
    fun mnumonic(keyCode: Int)
    fun mnumonic(character: Char)
    fun mnumonicIndex(index: Int)
    fun iconTextGap(px: Int)

    fun verticalTextPosition(spec: VerticalSpec.() -> Unit)
    fun horizontalTextPosition(spec: HorizontalSpec.() -> Unit)
    fun verticalAlignment(spec: VerticalSpec.() -> Unit)
    fun horizontalAlignment(spec: HorizontalSpec.() -> Unit)
    @UiBuilderDsl
    interface VerticalSpec {
        fun top()
        fun center()
        fun bottom()
    }

    @UiBuilderDsl
    interface HorizontalSpec {
        fun leading()
        fun left()
        fun center()
        fun right()
        fun trailing()
    }
}

