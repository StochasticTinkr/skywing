package com.stochastictinkr.skywing.components

import javax.swing.Action
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.RootPaneContainer
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun makeMenuBar(init: JMenuBar.() -> Unit): JMenuBar {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return JMenuBar().apply(init)
}

inline fun makeMenu(action: Action? = null, init: JMenu.() -> Unit = {}): JMenu {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return JMenu(action).apply(init)
}

inline fun makePopupMenu(init: JPopupMenu.() -> Unit = {}): JPopupMenu {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return JPopupMenu().apply(init)
}

inline fun makeMenuItem(action: Action? = null, init: JMenuItem.() -> Unit = {}): JMenuItem {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return JMenuItem(action).apply(init)
}

inline fun JMenuBar.addMenu(action: Action? = null, init: JMenu.() -> Unit = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenu(action, init))
}

inline fun JPopupMenu.addMenu(action: Action? = null, init: JMenu.() -> Unit = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenu(action, init))
}

inline fun JPopupMenu.addItem(action: Action? = null, init: JMenuItem.() -> Unit = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenuItem(action, init))
}

inline fun JMenu.addMenu(action: Action? = null, init: JMenu.() -> Unit = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenu(action, init))
}

inline fun JMenu.addItem(action: Action? = null, init: JMenuItem.() -> Unit = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenuItem(action, init))
}

inline fun RootPaneContainer.menuBar(init: JMenuBar.() -> Unit): JMenuBar {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return makeMenuBar(init).also { rootPane.jMenuBar = it }
}