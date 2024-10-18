package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.utils.Init
import javax.swing.Action
import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem
import javax.swing.JPopupMenu
import javax.swing.RootPaneContainer
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Create a [JMenuBar] and initialize it.
 */
inline fun makeMenuBar(init: Init<JMenuBar> = {}): JMenuBar {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return JMenuBar().apply(init)
}

/**
 * Create a [JMenu] and initialize it.
 * @param action An optional action to associate with this menu. See [JMenu.action].
 * @param text An optional text value to
 */
inline fun makeMenu(action: Action? = null, text: String? = null, init: Init<JMenu> = {}): JMenu {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return JMenu(action).apply {
        text?.let(::setText)
        init()
    }
}

/**
 * Create a [JPopupMenu] and initialize it.
 */
inline fun makePopupMenu(init: Init<JPopupMenu> = {}): JPopupMenu {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return JPopupMenu().apply(init)
}

/**
 * Create a [JMenuItem] and initialize it.
 * @param action An optional action to associate with this menu. See [JMenu.action].
 */
inline fun makeMenuItem(action: Action? = null, init: Init<JMenuItem> = {}): JMenuItem {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return JMenuItem(action).apply(init)
}

/**
 * Add a [JMenu] to the menu bar, and initialize it.
 * @param action An optional action to associate with this menu. See [JMenu.action].
 */
inline fun JMenuBar.menu(action: Action? = null, text: String? = null, init: Init<JMenu> = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenu(action, text) { init() })
}

/**
 * Add a [JMenu] to the popup menu, and initialize it.
 * @param action An optional action to associate with this menu. See [JMenu.action].
 */
inline fun JPopupMenu.menu(action: Action? = null, text: String? = null, init: Init<JMenu> = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenu(action, text) { init() })
}

/**
 * Add a [JMenu] to the menu, and initialize it.
 * @param action An optional action to associate with this menu. See [JMenu.action].
 */
inline fun JMenu.menu(action: Action? = null, text: String? = null, init: Init<JMenu> = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenu(action, text) { init() })
}


/**
 * Add a [JMenuItem] to the menu bar, and initialize it.
 * @param action An optional action to associate with this menu. See [JMenu.action].
 */
inline fun JMenuBar.item(action: Action? = null, init: Init<JMenuItem> = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenuItem(action) { init() })
}

/**
 * Add a [JMenuItem] to the popup menu, and initialize it.
 * @param action An optional action to associate with this menu. See [JMenu.action].
 */
inline fun JPopupMenu.item(action: Action? = null, init: Init<JMenuItem> = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenuItem(action) { init() })
}

/**
 * Add a [JMenuItem] to the menu, and initialize it.
 * @param action An optional action to associate with this menu. See [JMenu.action].
 */
inline fun JMenu.item(action: Action? = null, init: Init<JMenuItem> = {}) {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    add(makeMenuItem(action) { init() })
}

/**
 * Create a [JMenuBar], initialize it, and add it to this the root pane.
 */
inline fun RootPaneContainer.createMenuBar(init: Init<JMenuBar>): JMenuBar {
    contract { callsInPlace(init, InvocationKind.EXACTLY_ONCE) }
    return makeMenuBar(init).also { rootPane.jMenuBar = it }
}