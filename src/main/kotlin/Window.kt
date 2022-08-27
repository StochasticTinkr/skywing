package com.stoachstictinkr.skywing

import java.awt.Frame
import java.awt.GraphicsConfiguration
import java.awt.Window
import javax.swing.JWindow


fun Window.showPackedByPlatform() {
    isLocationByPlatform = true
    pack()
    isVisible = true
}

fun window(init: JWindow.() -> Unit) =
    JWindow().apply(init)

fun window(graphicsConfiguration: GraphicsConfiguration, init: JWindow.() -> Unit) =
    JWindow(graphicsConfiguration).apply(init)

fun window(owner: Window, init: JWindow.() -> Unit) = JWindow(owner).apply(init)
fun window(owner: Frame, init: JWindow.() -> Unit) = JWindow(owner).apply(init)

fun window(owner: Window, graphicsConfiguration: GraphicsConfiguration, init: JWindow.() -> Unit) =
    JWindow(owner, graphicsConfiguration).apply(init)

fun window(owner: Frame, graphicsConfiguration: GraphicsConfiguration, init: JWindow.() -> Unit) =
    JWindow(owner, graphicsConfiguration).apply(init)
