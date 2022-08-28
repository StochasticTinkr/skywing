package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.AbstractWindowBuilder
import com.stoachstictinkr.skywing.uibuilder.FrameSpec
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import java.awt.Frame

abstract  class AbstractFrameBuilder<C: Frame> : AbstractWindowBuilder<C>(), FrameSpec<C> {
    private var isResizable = true
    private var isUndecorated = false
    private var title = ""

    override fun title(title: String) {
        this.title = title
    }

    override fun notResizable() {
        isResizable = false
    }

    override fun resizable() {
        isResizable = true
    }

    override fun undecorated() {
        isUndecorated = true
    }

    override fun decorated() {
        isUndecorated = false
    }

    override fun configure(component: C, resolver: SpecResolver) {
        super.configure(component, resolver)
        component.title = title
        component.isResizable = isResizable
    }

    override fun configureBeforeOpacity(component: C, resolver: SpecResolver) {
        component.isUndecorated = isUndecorated
    }

}