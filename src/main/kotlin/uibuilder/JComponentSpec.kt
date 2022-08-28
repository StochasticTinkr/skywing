package com.stoachstictinkr.skywing.uibuilder

import javax.swing.JComponent
import javax.swing.border.Border

interface JComponentSpec<C : JComponent> : ComponentSpec<C> {
    fun border(border: Border)
    fun border(spec: BorderSpec.() -> Border)
}