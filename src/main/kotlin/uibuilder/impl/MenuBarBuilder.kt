package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.MenuBarSpec
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import java.awt.MenuBar

class MenuBarBuilder : MenuBarSpec {
    override fun configure(menuBar: MenuBar, resolver: SpecResolver) {
    }

    override fun getInstance(resolver: SpecResolver): MenuBar {
        return MenuBar()
    }

}
