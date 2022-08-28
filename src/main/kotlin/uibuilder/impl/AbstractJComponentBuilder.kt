package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.com.stoachstictinkr.skywing.com.stoachstictinkr.skywing.uibuilder.impl.BorderBuilder
import com.stoachstictinkr.skywing.uibuilder.BorderSpec
import com.stoachstictinkr.skywing.uibuilder.JComponentSpec
import javax.swing.JComponent
import javax.swing.border.Border

abstract class AbstractJComponentBuilder<C : JComponent> : AbstractComponentBuilder<C>(), JComponentSpec<C> {
    private var border: Border? = null
    override fun border(border: Border) {
        this.border = border
    }

    override fun border(spec: BorderSpec.() -> Border) {
        this.border = BorderBuilder().spec()
    }
}
