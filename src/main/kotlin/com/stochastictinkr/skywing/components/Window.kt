package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.utils.*
import java.awt.*
import java.awt.event.*

/**
 * Whether the window is in full screen mode.  This is determined by the [GraphicsDevice.fullScreenWindow] property.
 *
 * When setting this property, there are three possible outcomes:
 *
 * | New Value | Condition                          | Result                                                             |
 * |-----------|------------------------------------|--------------------------------------------------------------------|
 * | `true`    | Any                                | The window will be made fullscreen on its current [GraphicsDevice] |
 * | `false`   | The window is currently fullscreen | The window will be made not fullscreen                             |
 * | `false`   | The window is not fullscreen       | No operation                                                       |
 */
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

/**
 * Attach a listener that will be called when the window is closed.
 */
inline fun Window.onWindowClosed(crossinline block: (WindowEvent) -> Unit): Disposable {
    val listener = object : WindowAdapter() {
        override fun windowClosed(e: WindowEvent) {
            block(e)
        }
    }
    addWindowListener(listener)
    return Disposable { removeWindowListener(listener) }
}

/**
 * Configure the window and make it visible.
 */
fun <C : Window> C.configureAndShow(configure: C.() -> Unit) = apply {
    configure()
    isVisible = true
}
