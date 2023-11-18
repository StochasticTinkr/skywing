package com.stochastictinkr.skywing

import java.awt.Desktop
import java.awt.Desktop.Action
import java.awt.desktop.AboutEvent
import java.awt.desktop.PreferencesEvent
import java.awt.desktop.PrintFilesEvent
import java.awt.desktop.QuitEvent
import java.awt.desktop.QuitResponse
import java.awt.desktop.QuitStrategy

/**
 * The [Desktop] object.
 */
val desktop: Desktop get() = Desktop.getDesktop()

/**
 * Sets the About Handler. See [Desktop.setAboutHandler]
 */
fun Desktop.onAbout(handler: ((AboutEvent) -> Unit)?) = setAboutHandler(handler)

/**
 * Sets the Preferences Handler. See [Desktop.setPreferencesHandler]
 */
fun Desktop.onPreferences(handler: ((PreferencesEvent) -> Unit)?) = setPreferencesHandler(handler)

/**
 * Sets the Print File Handler. See [Desktop.setPrintFileHandler]
 */
fun Desktop.onPrintFile(handler: ((PrintFilesEvent) -> Unit)?) = setPrintFileHandler(handler)

/**
 * Sets the [QuitStrategy] and/or the quit handler. See [Desktop.setQuitHandler] and [Desktop.setQuitStrategy]
 */
fun Desktop.onQuit(quitStrategy: QuitStrategy? = null, handler: (QuitResponse.(QuitEvent) -> Unit)? = null) {
    handler?.let { setQuitHandler { e, response -> it(response, e) } }
    quitStrategy?.let { setQuitStrategy(it) }
}

/**
 * True if [Action.OPEN] is support. See [Desktop.isSupported]
 */
val Desktop.isOpenSupported: Boolean get() = isSupported(Action.OPEN)

/**
 * True if [Action.EDIT] is support. See [Desktop.isSupported]
 */
val Desktop.isEditSupported: Boolean get() = isSupported(Action.EDIT)

/**
 * True if [Action.PRINT] is support. See [Desktop.isSupported]
 */
val Desktop.isPrintSupported: Boolean get() = isSupported(Action.PRINT)

/**
 * True if [Action.MAIL] is support. See [Desktop.isSupported]
 */
val Desktop.isMailSupported: Boolean get() = isSupported(Action.MAIL)

/**
 * True if [Action.BROWSE] is support. See [Desktop.isSupported]
 */
val Desktop.isBrowseSupported: Boolean get() = isSupported(Action.BROWSE)

/**
 * True if [Action.APP_EVENT_FOREGROUND] is support. See [Desktop.isSupported]
 */
val Desktop.isAppEventForegroundSupported: Boolean get() = isSupported(Action.APP_EVENT_FOREGROUND)

/**
 * True if [Action.APP_EVENT_HIDDEN] is support. See [Desktop.isSupported]
 */
val Desktop.isAppEventHiddenSupported: Boolean get() = isSupported(Action.APP_EVENT_HIDDEN)

/**
 * True if [Action.APP_EVENT_REOPENED] is support. See [Desktop.isSupported]
 */
val Desktop.isAppEventReopenedSupported: Boolean get() = isSupported(Action.APP_EVENT_REOPENED)

/**
 * True if [Action.APP_EVENT_SCREEN_SLEEP] is support. See [Desktop.isSupported]
 */
val Desktop.isAppEventScreenSleepSupported: Boolean get() = isSupported(Action.APP_EVENT_SCREEN_SLEEP)

/**
 * True if [Action.APP_EVENT_SYSTEM_SLEEP] is support. See [Desktop.isSupported]
 */
val Desktop.isAppEventSystemSleepSupported: Boolean get() = isSupported(Action.APP_EVENT_SYSTEM_SLEEP)

/**
 * True if [Action.APP_EVENT_USER_SESSION] is support. See [Desktop.isSupported]
 */
val Desktop.isAppEventUserSessionSupported: Boolean get() = isSupported(Action.APP_EVENT_USER_SESSION)

/**
 * True if [Action.APP_ABOUT] is support. See [Desktop.isSupported]
 */
val Desktop.isAppAboutSupported: Boolean get() = isSupported(Action.APP_ABOUT)

/**
 * True if [Action.APP_PREFERENCES] is support. See [Desktop.isSupported]
 */
val Desktop.isAppPreferencesSupported: Boolean get() = isSupported(Action.APP_PREFERENCES)

/**
 * True if [Action.APP_OPEN_FILE] is support. See [Desktop.isSupported]
 */
val Desktop.isAppOpenFileSupported: Boolean get() = isSupported(Action.APP_OPEN_FILE)

/**
 * True if [Action.APP_PRINT_FILE] is support. See [Desktop.isSupported]
 */
val Desktop.isAppPrintFileSupported: Boolean get() = isSupported(Action.APP_PRINT_FILE)

/**
 * True if [Action.APP_OPEN_URI] is support. See [Desktop.isSupported]
 */
val Desktop.isAppOpenUriSupported: Boolean get() = isSupported(Action.APP_OPEN_URI)

/**
 * True if [Action.APP_QUIT_HANDLER] is support. See [Desktop.isSupported]
 */
val Desktop.isAppQuitHandlerSupported: Boolean get() = isSupported(Action.APP_QUIT_HANDLER)

/**
 * True if [Action.APP_QUIT_STRATEGY] is support. See [Desktop.isSupported]
 */
val Desktop.isAppQuitStrategySupported: Boolean get() = isSupported(Action.APP_QUIT_STRATEGY)

/**
 * True if [Action.APP_SUDDEN_TERMINATION] is support. See [Desktop.isSupported]
 */
val Desktop.isAppSuddenTerminationSupported: Boolean get() = isSupported(Action.APP_SUDDEN_TERMINATION)

/**
 * True if [Action.APP_REQUEST_FOREGROUND] is support. See [Desktop.isSupported]
 */
val Desktop.isAppRequestForegroundSupported: Boolean get() = isSupported(Action.APP_REQUEST_FOREGROUND)

/**
 * True if [Action.APP_HELP_VIEWER] is support. See [Desktop.isSupported]
 */
val Desktop.isAppHelpViewerSupported: Boolean get() = isSupported(Action.APP_HELP_VIEWER)

/**
 * True if [Action.APP_MENU_BAR] is support. See [Desktop.isSupported]
 */
val Desktop.isAppMenuBarSupported: Boolean get() = isSupported(Action.APP_MENU_BAR)

/**
 * True if [Action.BROWSE_FILE_DIR] is support. See [Desktop.isSupported]
 */
val Desktop.isBrowseFileDirSupported: Boolean get() = isSupported(Action.BROWSE_FILE_DIR)

/**
 * True if [Action.MOVE_TO_TRASH] is support. See [Desktop.isSupported]
 */
val Desktop.isMoveToTrashSupported: Boolean get() = isSupported(Action.MOVE_TO_TRASH)