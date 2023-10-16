package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.utils.Disposable
import java.awt.Window
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame


var JFrame.onClose
    get() = CloseOperation.entries[defaultCloseOperation]
    set(value) {
        defaultCloseOperation = value.ordinal
    }

fun JFrame.mainFrame(maximize: Boolean = true, closeOperation: CloseOperation = CloseOperation.EXIT) {
    onClose = closeOperation
    if (maximize) {
        extendedState = JFrame.MAXIMIZED_BOTH
    }
}

