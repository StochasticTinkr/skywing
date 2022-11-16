package com.stochastictinkr.skywing.swing

import javax.swing.Action
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JPopupMenu

fun makeMenuBar(init: JMenuBar.() -> Unit) = JMenuBar().apply(init)
fun makeMenu(action: Action? = null, init: JMenu.() -> Unit = {}) = JMenu(action).apply(init)
fun makePopupMenu(init: JPopupMenu.() -> Unit = {}) = JPopupMenu().apply(init)
fun makeMenuItem(action: Action? = null, init: JMenuItem.() -> Unit = {}) = JMenuItem(action).apply(init)

fun JMenuBar.addMenu(action: Action? = null, init: JMenu.() -> Unit = {}) {
    add(makeMenu(action, init))
}

fun JPopupMenu.addMenu(action: Action? = null, init: JMenu.() -> Unit = {}) {
    add(makeMenu(action, init))
}
fun JPopupMenu.addItem(action: Action? = null, init: JMenuItem.() -> Unit = {}) {
    add(makeMenuItem(action, init))
}

fun JMenu.addMenu(action: Action? = null, init: JMenu.() -> Unit = {}) {
    add(makeMenu(action, init))
}

fun JMenu.addItem(action: Action? = null, init: JMenuItem.() -> Unit = {}) {
    add(makeMenuItem(action, init))
}

