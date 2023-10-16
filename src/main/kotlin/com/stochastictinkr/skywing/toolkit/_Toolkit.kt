package com.stochastictinkr.skywing.toolkit

import java.awt.Toolkit
import java.awt.event.KeyEvent

val toolkit get():Toolkit = Toolkit.getDefaultToolkit()

var Toolkit.isCapsLockOn: Boolean by LockingState(KeyEvent.VK_CAPS_LOCK)
var Toolkit.isNumLockOn: Boolean by LockingState(KeyEvent.VK_NUM_LOCK)
var Toolkit.isScrollLockOn: Boolean by LockingState(KeyEvent.VK_SCROLL_LOCK)
var Toolkit.isKanaLockOn: Boolean by LockingState(KeyEvent.VK_KANA_LOCK)

@JvmInline
private value class LockingState(val keyCode: Int) {
    operator fun getValue(toolkit: Toolkit, property: Any) = toolkit.getLockingKeyState(keyCode)
    operator fun setValue(toolkit: Toolkit, property: Any, value: Boolean) {
        toolkit.setLockingKeyState(keyCode, value)
    }
}