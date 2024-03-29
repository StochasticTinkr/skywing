package com.stochastictinkr.skywing

import java.awt.Toolkit
import java.awt.event.KeyEvent

/**
 * The default toolkit.
 */
val toolkit get():Toolkit = Toolkit.getDefaultToolkit()

/**
 * The on/off state of the caps lock key. See [Toolkit.getLockingKeyState] and [KeyEvent.VK_CAPS_LOCK]
 */
var Toolkit.isCapsLockOn: Boolean by LockingState(KeyEvent.VK_CAPS_LOCK)

/**
 * The on/off state of the num lock key. See [Toolkit.getLockingKeyState] and [KeyEvent.VK_NUM_LOCK]
 */
var Toolkit.isNumLockOn: Boolean by LockingState(KeyEvent.VK_NUM_LOCK)

/**
 * The on/off state of the scroll lock key. See [Toolkit.getLockingKeyState] and [KeyEvent.VK_SCROLL_LOCK]
 */
var Toolkit.isScrollLockOn: Boolean by LockingState(KeyEvent.VK_SCROLL_LOCK)

/**
 * The on/off state of the kana lock key. See [Toolkit.getLockingKeyState] and [KeyEvent.VK_KANA_LOCK]
 */
var Toolkit.isKanaLockOn: Boolean by LockingState(KeyEvent.VK_KANA_LOCK)

/**
 * Property Delegate for a specific key-code locking state.
 */
@JvmInline
private value class LockingState(val keyCode: Int) {
    operator fun getValue(toolkit: Toolkit, property: Any) = toolkit.getLockingKeyState(keyCode)
    operator fun setValue(toolkit: Toolkit, property: Any, value: Boolean) {
        toolkit.setLockingKeyState(keyCode, value)
    }
}