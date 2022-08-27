package com.stoachstictinkr.skywing

import java.awt.GraphicsConfiguration
import javax.swing.JFrame
import javax.swing.WindowConstants

fun frame(title: String = "", graphicsConfiguration: GraphicsConfiguration? = null, init: JFrame.() -> Unit) =
    JFrame(title, graphicsConfiguration).apply(init)

fun JFrame.doNothingOnClose() {
    defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
}

fun JFrame.hidOnClose() {
    defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
}

fun JFrame.disposeOnClose() {
    defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
}

fun JFrame.exitOnClose() {
    defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
}
