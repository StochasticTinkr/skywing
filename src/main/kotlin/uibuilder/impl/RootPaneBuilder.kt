package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.JMenuBarSpec
import com.stochastictinkr.skywing.uibuilder.RootPaneSpec
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import javax.swing.RootPaneContainer

class RootPaneBuilder : RootPaneSpec {
    private var jMenuBar: JMenuBarSpec? = null
    override fun jMenuBar(spec: JMenuBarSpec.() -> Unit) {
        jMenuBar = JMenuBarBuilder()
    }

    override fun configureRootPane(rootPaneContainer: RootPaneContainer, resolver: SpecResolver) {

    }
}
