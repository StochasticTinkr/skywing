package com.stochastictinkr.skywing

import java.awt.EventQueue.invokeLater
import javax.swing.UIManager

fun initSkywing() {
    System.setProperty("apple.awt.application.appearance", "system")
    System.setProperty("apple.laf.useScreenMenuBar", "true")
    invokeLater {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
    }
}
