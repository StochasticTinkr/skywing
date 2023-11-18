package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.utils.ObservableProperty
import javax.swing.Icon
import javax.swing.JCheckBox

/**
 * Create a JCheckBox.
 */
fun jCheckBox(text: String? = null, icon: Icon? = null, selected: Boolean = false) =
    JCheckBox(text, icon, selected)

/**
 * Bind the state of this checkbox to an ObservableProperty.
 */
infix fun JCheckBox.bindTo(property: ObservableProperty<Boolean>) {
    isSelected = property.get()
    addChangeListener {
        property.set(isSelected)
    }
    property.addListener { isSelected = it }
}
