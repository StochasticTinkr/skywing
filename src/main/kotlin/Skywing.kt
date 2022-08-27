package com.stoachstictinkr.skywing

import java.awt.Component
import java.awt.Container
import java.awt.Dimension
import java.awt.EventQueue
import javax.swing.UIManager

fun initSkying(init: Desktop.() -> Unit = {}) {
    System.setProperty("apple.awt.application.appearance", "system")
    System.setProperty("apple.laf.useScreenMenuBar", "true")
    Desktop.init()
    invokeLater {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    }
}

fun invokeLater(run: Runnable) = EventQueue.invokeLater(run)
fun invokeAndWait(run: Runnable) = EventQueue.invokeAndWait(run)
val isEventDispatchThread get() = EventQueue.isDispatchThread()

object NoContainer : Container()

inline fun <T> doNotAdd(run: Container.() -> T): T {
    val result = NoContainer.run()
    NoContainer.removeAll()
    return result
}

infix fun Int.by(height: Int) = Dimension(this, height)

fun Container.maybeAdd(component: Component) = if (this == NoContainer) component else component.also(::add)


