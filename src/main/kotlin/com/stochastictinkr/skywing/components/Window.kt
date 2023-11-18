package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.utils.Disposable
import java.awt.Window
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

var Window.isFullScreen: Boolean
    get() =
        graphicsConfiguration.device.fullScreenWindow == this
    set(value) {
        when {
            value -> graphicsConfiguration.device.fullScreenWindow = this
            isFullScreen -> graphicsConfiguration.device.fullScreenWindow = null
            else -> Unit
        }
    }

inline fun Window.onWindowClosed(crossinline block: (WindowEvent) -> Unit): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowClosed(e: WindowEvent) {
            block(e)
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}

fun <C : Window> C.configureAndShow(configure: C.() -> Unit) = apply {
    configure()
    isVisible = true
}
