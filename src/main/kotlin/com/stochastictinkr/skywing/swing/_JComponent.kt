package com.stochastictinkr.skywing.swing

import javax.swing.Action
import javax.swing.JComponent
import javax.swing.JPopupMenu
import javax.swing.border.Border

inline fun <T : Border> JComponent.border(build: BorderFactory.() -> T): T {
    return BorderFactory.build().also { border = it }
}

fun JComponent.popupMenu(init: JPopupMenu.() -> Unit) {
    this.componentPopupMenu = makePopupMenu(init)
}

/**
 * Adds an accelerated action to the inputMap and actionMap of this JComponent
 */
fun JComponent.addAction(action: Action) {
    inputMap.put(action.accelerator, action.actionCommand)
    actionMap.put(action.actionCommand, action)
}
