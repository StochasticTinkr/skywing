package com.stochastictinkr.skywing

import java.awt.EventQueue.invokeLater
import javax.swing.UIManager

/**
 * Initialize the UI.  This should ideally be called before the [java.awt.Toolkit] is initialized, as it sets
 * some system properties that are used on initialization.
 *
 * This will also set the look and feel to the system look and feel.
 *
 * @param applicationName This is used to set the application name, which may be visible in some UIs.
 */
fun initSkywing(applicationName: String? = null) {
    if (applicationName != null) {
        System.setProperty("apple.awt.application.name", applicationName)
    }
    System.setProperty("apple.awt.application.appearance", "system")
    System.setProperty("apple.laf.useScreenMenuBar", "true")
    invokeLater {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    }
}

