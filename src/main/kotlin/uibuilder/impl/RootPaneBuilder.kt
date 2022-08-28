package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.uibuilder.JMenuBarSpec
import com.stoachstictinkr.skywing.uibuilder.RootPaneSpec
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import javax.swing.RootPaneContainer

class RootPaneBuilder : RootPaneSpec {
    private var jMenuBar: JMenuBarSpec? = null
    override fun jMenuBar(spec: JMenuBarSpec.() -> Unit) {
        jMenuBar = JMenuBarBuilder()
    }

    override fun configureRootPane(rootPaneContainer: RootPaneContainer, resolver: SpecResolver) {

    }
}
