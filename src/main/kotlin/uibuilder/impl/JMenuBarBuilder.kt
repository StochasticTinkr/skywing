package com.stochastictinkr.skywing.uibuilder.impl

import com.stochastictinkr.skywing.uibuilder.JMenuBarSpec
import com.stochastictinkr.skywing.uibuilder.SpecResolver
import javax.swing.JMenuBar

class JMenuBarBuilder : JMenuBarSpec {
    override fun getInstance(resolver: SpecResolver): JMenuBar = JMenuBar()
}
