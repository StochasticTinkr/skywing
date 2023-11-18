package com.stochastictinkr.skywing.components

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

