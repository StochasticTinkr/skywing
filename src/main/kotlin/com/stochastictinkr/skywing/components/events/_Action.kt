package com.stochastictinkr.skywing.components.events

import java.awt.event.ActionEvent
import javax.swing.AbstractAction
import javax.swing.AbstractButton
import javax.swing.Action
import javax.swing.Icon
import javax.swing.KeyStroke
import kotlin.reflect.KProperty

/**
 * Property for accessing [Action.NAME]
 */
var Action.name: String? by actionKey(Action.NAME)

/**
 * Property for accessing [Action.SMALL_ICON]
 */
var Action.smallIcon: Icon? by actionKey(Action.SMALL_ICON)

/**
 * Property for accessing [Action.LARGE_ICON_KEY]
 */
var Action.largeIcon: Icon? by actionKey(Action.LARGE_ICON_KEY)

/**
 * Property for accessing [Action.SHORT_DESCRIPTION]
 */
var Action.shortDescription: String? by actionKey(Action.SHORT_DESCRIPTION)

/**
 * Property for accessing [Action.LONG_DESCRIPTION]
 */
var Action.longDescription: String? by actionKey(Action.LONG_DESCRIPTION)

/**
 * Property for accessing [Action.ACTION_COMMAND_KEY]
 */
var Action.actionCommand: String? by actionKey(Action.ACTION_COMMAND_KEY)

/**
 * Property for accessing [Action.ACCELERATOR_KEY]
 */
var Action.accelerator: KeyStroke? by actionKey(Action.ACCELERATOR_KEY)

/**
 * Property for accessing [Action.MNEMONIC_KEY]
 */
var Action.mnemonicKeyCode: Int? by actionKey(Action.MNEMONIC_KEY)

/**
 * Property for accessing [Action.SELECTED_KEY]
 */
var Action.isSelected: Boolean by ActionKey(Action.SELECTED_KEY, false)

/**
 * Property for accessing [Action.DISPLAYED_MNEMONIC_INDEX_KEY]
 */
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
    /** Initial value associated with the [Action.NAME].*/
    name: String? = null,
    /** Initial value associated with the [Action.SMALL_ICON].*/
    smallIcon: Icon? = null,
    /** Initial value associated with the [Action.LARGE_ICON_KEY].*/
    largeIcon: Icon? = null,
    /** Initial value associated with the [Action.SHORT_DESCRIPTION].*/
    shortDescription: String? = null,
    /** Initial value associated with the [Action.LONG_DESCRIPTION].*/
    longDescription: String? = null,
    /** Initial value associated with the [Action.ACTION_COMMAND_KEY].*/
    actionCommand: String? = null,
    /** Initial value associated with the [Action.ACCELERATOR_KEY].*/
    accelerator: KeyStroke? = null,
    /** Initial value associated with the [Action.MNEMONIC_KEY].*/
    mnemonicKeyCode: Int? = null,
    /** Initial value associated with the [Action.SELECTED_KEY].*/
    isSelected: Boolean = false,
    /** Initial value associated with the [Action.DISPLAYED_MNEMONIC_INDEX_KEY].*/
    displayedMnemonicIndex: Int? = null,
    /** Initial value of the [Action.isEnabled] property.*/
    isEnabled: Boolean = true,
    /** The code to execute to perform the action.*/
    actionPerformed: (ActionEvent) -> Unit = {},
): Action = ActionImpl(actionPerformed).apply {
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

fun AbstractButton.withAction(
    /** Initial value associated with the [Action.NAME].*/
    name: String? = null,
    /** Initial value associated with the [Action.SMALL_ICON].*/
    smallIcon: Icon? = null,
    /** Initial value associated with the [Action.LARGE_ICON_KEY].*/
    largeIcon: Icon? = null,
    /** Initial value associated with the [Action.SHORT_DESCRIPTION].*/
    shortDescription: String? = null,
    /** Initial value associated with the [Action.LONG_DESCRIPTION].*/
    longDescription: String? = null,
    /** Initial value associated with the [Action.ACTION_COMMAND_KEY].*/
    actionCommand: String? = null,
    /** Initial value associated with the [Action.ACCELERATOR_KEY].*/
    accelerator: KeyStroke? = null,
    /** Initial value associated with the [Action.MNEMONIC_KEY].*/
    mnemonicKeyCode: Int? = null,
    /** Initial value associated with the [Action.SELECTED_KEY].*/
    isSelected: Boolean = false,
    /** Initial value associated with the [Action.DISPLAYED_MNEMONIC_INDEX_KEY].*/
    displayedMnemonicIndex: Int? = null,
    /** Initial value of the [Action.isEnabled] property.*/
    isEnabled: Boolean = true,
    /** The code to execute to perform the action.*/
    actionPerformed: (ActionEvent) -> Unit = {},
) = also { button ->
    button.action = action(
        name,
        smallIcon,
        largeIcon,
        shortDescription,
        longDescription,
        actionCommand,
        accelerator,
        mnemonicKeyCode,
        isSelected,
        displayedMnemonicIndex,
        isEnabled,
        actionPerformed
    )
}


private class ActionImpl(private val handleAction: (ActionEvent) -> Unit) : AbstractAction() {
    override fun actionPerformed(e: ActionEvent) {
        handleAction(e)
    }
}



