package com.stochastictinkr.skywing.swing

import javax.swing.InputMap
import javax.swing.JComponent
import javax.swing.JPopupMenu
import javax.swing.border.Border

inline fun <T : Border> JComponent.border(build: BorderFactory.() -> T): T {
    return BorderFactory.build().also { border = it }
}

fun JComponent.popupMenu(init: JPopupMenu.() -> Unit) {
    this.componentPopupMenu = makePopupMenu(init)
}