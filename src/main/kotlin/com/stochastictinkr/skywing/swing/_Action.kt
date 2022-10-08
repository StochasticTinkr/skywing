package com.stochastictinkr.skywing.swing

import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.Action
import javax.swing.Icon
import javax.swing.KeyStroke
import kotlin.reflect.KProperty


var Action.name: String? by actionKey(Action.NAME)
var Action.smallIcon: Icon? by actionKey(Action.SMALL_ICON)
var Action.largeIcon: Icon? by actionKey(Action.LARGE_ICON_KEY)
var Action.shortDescription: String? by actionKey(Action.SHORT_DESCRIPTION)
var Action.longDescription: String? by actionKey(Action.LONG_DESCRIPTION)
var Action.actionCommand: String? by actionKey(Action.ACTION_COMMAND_KEY)
var Action.accelerator: KeyStroke? by actionKey(Action.ACCELERATOR_KEY)
var Action.mnemonicKeyCode: Int? by actionKey(Action.MNEMONIC_KEY)
var Action.isSelected: Boolean by ActionKey(Action.SELECTED_KEY, false)
var Action.displayedMnemonicIndex: Int? by actionKey(Action.DISPLAYED_MNEMONIC_INDEX_KEY)

private fun <T : Any> actionKey(name: String) = ActionKey<T?>(name, null)

private class ActionKey<T>(private val name: String, private val default: T) {
    @Suppress("UNCHECKED_CAST")
    operator fun getValue(action: Action, property: KProperty<*>): T = action.getValue(name) as T ?: default
    operator fun setValue(action: Action, property: KProperty<*>, value: T) {
        action.putValue(name, value)
    }
}

fun action(
    name: String? = null,
    smallIcon: Icon? = null,
    isEnabled: Boolean = true,
    largeIcon: Icon? = null,
    shortDescription: String? = null,
    longDescription: String? = null,
    actionCommand: String? = null,
    accelerator: KeyStroke? = null,
    mnemonicKeyCode: Int? = null,
    isSelected: Boolean = false,
    displayedMnemonicIndex: Int? = null,
    actionPerformed: (ActionEvent) -> Unit = {},
):Action = ActionImpl(actionPerformed).apply {
    this.name = name
    this.isEnabled = isEnabled
    this.smallIcon = smallIcon
    this.largeIcon = largeIcon
    this.shortDescription = shortDescription
    this.longDescription = longDescription
    this.actionCommand = actionCommand
    this.accelerator = accelerator
    this.mnemonicKeyCode = mnemonicKeyCode
    this.isSelected = isSelected
    this.displayedMnemonicIndex = displayedMnemonicIndex
}

private class ActionImpl(private val handleAction: (ActionEvent) -> Unit) : AbstractAction() {
    override fun actionPerformed(e: ActionEvent) {
        handleAction(e)
    }
}