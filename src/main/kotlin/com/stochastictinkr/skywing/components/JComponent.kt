package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.components.events.accelerator
import com.stochastictinkr.skywing.components.events.actionCommand
import com.stochastictinkr.skywing.utils.Init
import javax.swing.Action
import javax.swing.JComponent
import javax.swing.JPopupMenu
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Initializes and sets the popup menu for this component.  See [JComponent.setComponentPopupMenu] and [makePopupMenu]
 */
inline fun JComponent.makePopupMenu(init: Init<JPopupMenu>) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    this.componentPopupMenu = com.stochastictinkr.skywing.components.makePopupMenu(init)
}

/**
 * Adds an accelerated action to the inputMap and actionMap of this JComponent.
 */
fun JComponent.addAction(action: Action) {
    inputMap.put(action.accelerator, action.actionCommand)
    actionMap.put(action.actionCommand, action)
}
