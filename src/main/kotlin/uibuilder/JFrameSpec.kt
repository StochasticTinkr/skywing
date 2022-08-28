package com.stochastictinkr.skywing.uibuilder

import javax.swing.JFrame

interface JFrameSpec : FrameSpec<JFrame>, RootPaneSpec {
    fun doNothingOnClose()
    fun hideOnClose()
    fun disposeOnClose()
    fun exitOnClose()
}
