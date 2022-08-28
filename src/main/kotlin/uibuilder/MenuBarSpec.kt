package com.stoachstictinkr.skywing.uibuilder

import java.awt.MenuBar

interface MenuBarSpec : SpecRef<MenuBar> {
    fun configure(menuBar: MenuBar, resolver: SpecResolver)
}
