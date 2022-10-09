package com.stochastictinkr.skywing.swing

import javax.swing.JFrame
import javax.swing.JMenuBar


fun JFrame.menuBar(init: JMenuBar.() -> Unit) = makeMenuBar(init).also {
    jMenuBar = it
}