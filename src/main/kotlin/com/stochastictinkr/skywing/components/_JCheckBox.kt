package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.utils.ObservableProperty
import javax.swing.Action
import javax.swing.Icon
import javax.swing.JCheckBox
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun checkbox(text: String? = null, icon: Icon? = null, selected: Boolean = false) =
    JCheckBox(text, icon, selected)

infix fun JCheckBox.bindTo(property: ObservableProperty<Boolean>) {
    isSelected = property.get()
    addChangeListener {
        property.set(isSelected)
    }
    property.addListener { isSelected = it }
}
