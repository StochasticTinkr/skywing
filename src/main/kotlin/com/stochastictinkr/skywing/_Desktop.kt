package com.stochastictinkr.skywing

import java.awt.Desktop
import java.awt.Desktop.Action
import java.awt.desktop.AboutEvent
import java.awt.desktop.PreferencesEvent
import java.awt.desktop.PrintFilesEvent
import java.awt.desktop.QuitEvent
import java.awt.desktop.QuitResponse
import java.awt.desktop.QuitStrategy

val desktop: Desktop get() = Desktop.getDesktop()
fun Desktop.onAbout(handler: ((AboutEvent) -> Unit)?) = setAboutHandler(handler)
fun Desktop.onPreferences(handler: ((PreferencesEvent) -> Unit)?) = setPreferencesHandler(handler)
fun Desktop.onPrintFile(handler: ((PrintFilesEvent) -> Unit)?) = setPrintFileHandler(handler)
fun Desktop.onQuit(quitStrategy: QuitStrategy? = null, handler: (QuitResponse.(QuitEvent) -> Unit)? = null) {
    handler?.let { setQuitHandler { e, response -> it(response, e) } }
    quitStrategy?.let { setQuitStrategy(it) }
}

val Desktop.isOpenSupported: Boolean get() = isSupported(Action.OPEN)
val Desktop.isEditSupported: Boolean get() = isSupported(Action.EDIT)
val Desktop.isPrintSupported: Boolean get() = isSupported(Action.PRINT)
val Desktop.isMailSupported: Boolean get() = isSupported(Action.MAIL)
val Desktop.isBrowseSupported: Boolean get() = isSupported(Action.BROWSE)
val Desktop.isAppEventForegroundSupported: Boolean get() = isSupported(Action.APP_EVENT_FOREGROUND)
val Desktop.isAppEventHiddenSupported: Boolean get() = isSupported(Action.APP_EVENT_HIDDEN)
val Desktop.isAppEventReopenedSupported: Boolean get() = isSupported(Action.APP_EVENT_REOPENED)
val Desktop.isAppEventScreenSleepSupported: Boolean get() = isSupported(Action.APP_EVENT_SCREEN_SLEEP)
val Desktop.isAppEventSystemSleepSupported: Boolean get() = isSupported(Action.APP_EVENT_SYSTEM_SLEEP)
val Desktop.isAppEventUserSessionSupported: Boolean get() = isSupported(Action.APP_EVENT_USER_SESSION)
val Desktop.isAppAboutSupported: Boolean get() = isSupported(Action.APP_ABOUT)
val Desktop.isAppPreferencesSupported: Boolean get() = isSupported(Action.APP_PREFERENCES)
val Desktop.isAppOpenFileSupported: Boolean get() = isSupported(Action.APP_OPEN_FILE)
val Desktop.isAppPrintFileSupported: Boolean get() = isSupported(Action.APP_PRINT_FILE)
val Desktop.isAppOpenUriSupported: Boolean get() = isSupported(Action.APP_OPEN_URI)
val Desktop.isAppQuitHandlerSupported: Boolean get() = isSupported(Action.APP_QUIT_HANDLER)
val Desktop.isAppQuitStrategySupported: Boolean get() = isSupported(Action.APP_QUIT_STRATEGY)
val Desktop.isAppSuddenTerminationSupported: Boolean get() = isSupported(Action.APP_SUDDEN_TERMINATION)
val Desktop.isAppRequestForegroundSupported: Boolean get() = isSupported(Action.APP_REQUEST_FOREGROUND)
val Desktop.isAppHelpViewerSupported: Boolean get() = isSupported(Action.APP_HELP_VIEWER)
val Desktop.isAppMenuBarSupported: Boolean get() = isSupported(Action.APP_MENU_BAR)
val Desktop.isBrowseFileDirSupported: Boolean get() = isSupported(Action.BROWSE_FILE_DIR)
val Desktop.isMoveToTrashSupported: Boolean get() = isSupported(Action.MOVE_TO_TRASH)