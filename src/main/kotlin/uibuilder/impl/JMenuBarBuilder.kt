package com.stoachstictinkr.skywing.uibuilder.impl

import com.stoachstictinkr.skywing.uibuilder.JMenuBarSpec
import com.stoachstictinkr.skywing.uibuilder.SpecResolver
import javax.swing.JMenuBar

class JMenuBarBuilder : JMenuBarSpec {
    override fun getInstance(resolver: SpecResolver): JMenuBar = JMenuBar()
}
