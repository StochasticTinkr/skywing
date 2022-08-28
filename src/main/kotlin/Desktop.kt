package com.stochastictinkr.skywing

import java.awt.desktop.AboutEvent
import java.awt.desktop.PreferencesEvent
import java.awt.desktop.PrintFilesEvent
import java.awt.desktop.QuitEvent
import java.awt.desktop.QuitResponse
import java.awt.desktop.QuitStrategy
import java.awt.Desktop as AwtDesktop

object Desktop {
    val awtDesktop by lazy { AwtDesktop.getDesktop()!! }

    fun onAbout(handler: (AboutEvent) -> Unit) = awtDesktop.setAboutHandler(handler)
    fun onPreferences(handler: (PreferencesEvent) -> Unit) = awtDesktop.setPreferencesHandler(handler)
    fun onPrintFile(handler: (PrintFilesEvent) -> Unit) = awtDesktop.setPrintFileHandler(handler)
    fun onQuit(quitStrategy: QuitStrategy? = null, handler: (QuitResponse.(QuitEvent) -> Unit)? = null) {
        if (handler != null)
            awtDesktop.setQuitHandler { e, response -> response.handler(e) }
        if (quitStrategy != null)
            awtDesktop.setQuitStrategy(quitStrategy)
    }

    fun enableSuddenTermination() = awtDesktop.enableSuddenTermination()
    fun disableSuddenTermination() = awtDesktop.disableSuddenTermination()


}