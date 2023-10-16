package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.components.events.accelerator
import com.stochastictinkr.skywing.components.events.actionCommand
import javax.swing.Action
import javax.swing.JComponent
import javax.swing.JPopupMenu

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
