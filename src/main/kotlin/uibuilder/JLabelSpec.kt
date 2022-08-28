package com.stoachstictinkr.skywing.uibuilder

import com.stoachstictinkr.skywing.uibuilder.impl.JLabelBuilder
import javax.swing.Icon
import javax.swing.JLabel

interface JLabelSpec : ComponentSpec<JLabel> {
    fun text(text: String)
    fun icon(icon: Icon)
    fun alignLeft()
    fun alignCenter()
    fun alignRight()
    fun alignLeading()
    fun alignTrailing()
    fun icon(iconSpec: IconSpec.() -> Icon) = icon(IconSpec().iconSpec())
}

fun ContainerSpec.label(spec: JLabelSpec.() -> Unit): SpecRef<JLabel> =
    JLabelBuilder().apply(spec).apply(::add)
