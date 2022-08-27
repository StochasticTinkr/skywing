package com.stoachstictinkr.skywing

import java.awt.Container
import javax.swing.Icon
import javax.swing.JLabel
import javax.swing.SwingConstants

enum class Orientation(internal val swingConstant: Int) {
    CENTER(SwingConstants.CENTER),
    LEFT(SwingConstants.LEFT),
    RIGHT(SwingConstants.RIGHT),
    LEADING(SwingConstants.LEADING),
    TRAILING(SwingConstants.TRAILING)
}

fun Container.label(init: JLabel.() -> Unit) =
    add(JLabel().apply(init))

fun Container.label(text: String, orientation: Orientation = Orientation.LEADING, init: JLabel.() -> Unit) =
    add(JLabel(text, orientation.swingConstant).apply(init))

fun Container.label(icon: Icon, orientation: Orientation = Orientation.CENTER, init: JLabel.() -> Unit) =
    add(JLabel(icon, orientation.swingConstant).apply(init))

fun Container.label(text: String, icon: Icon, orientation: Orientation, init: JLabel.() -> Unit) =
    add(JLabel(text, icon, orientation.swingConstant).apply(init))
