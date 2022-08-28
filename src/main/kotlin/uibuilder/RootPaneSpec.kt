package com.stoachstictinkr.skywing.uibuilder

import javax.swing.RootPaneContainer

interface RootPaneSpec {
    fun jMenuBar(spec: JMenuBarSpec.() -> Unit)
    fun configureRootPane(rootPaneContainer: RootPaneContainer, resolver: SpecResolver)
}