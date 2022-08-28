package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.uibuilder.MenuBarSpec
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import java.awt.MenuBar

class MenuBarBuilder : MenuBarSpec {
    override fun configure(menuBar: MenuBar, resolver: SpecResolver) {
    }

    override fun getInstance(resolver: SpecResolver): MenuBar {
        return MenuBar()
    }

}
