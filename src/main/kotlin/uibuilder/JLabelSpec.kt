package com.stoachstictinkr.skywing.uibuilder

import javax.swing.Icon
import javax.swing.JLabel

interface JLabelSpec : JComponentSpec<JLabel> {
    fun text(text: String)
    fun icon(icon: Icon)
    fun alignLeft()
    fun alignCenter()
    fun alignRight()
    fun alignLeading()
    fun alignTrailing()
    fun icon(builder: IconSpec.() -> Icon)
}

