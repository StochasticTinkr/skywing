package com.stochastictinkr.skywing.uibuilder.dsl.impl

import com.stochastictinkr.skywing.uibuilder.dsl.JFrameConfig
import com.stochastictinkr.skywing.uibuilder.dsl.WindowConfig
import javax.swing.JFrame
import javax.swing.WindowConstants

fun jFrameConfigurer(): Configurer<JFrameConfig, JFrame> =
    JFrameConfigurer().asConfigurer { frame ->
        frame.defaultCloseOperation = defaultCloseOperation
        frame.title = title
        frame.isResizable = resizable
        frame.isUndecorated = undecorated
        windowConfigurer.configure(frame)
    }

private class JFrameConfigurer(val windowConfigurer: Configurer<WindowConfig, in JFrame> = windowConfigurer()) :
    JFrameConfig, WindowConfig by windowConfigurer.config {
    var defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
    var title: String? = "Untitled"
    var resizable = true
    var undecorated = false

    override fun doNothingOnClose() {
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
    }

    override fun hideOnClose() {
        defaultCloseOperation = WindowConstants.HIDE_ON_CLOSE
    }

    override fun disposeOnClose() {
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
    }

    override fun exitOnClose() {
        defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    }

    override fun title(title: String) {
        this.title = title;
    }

    override fun notResizable() {
        resizable = false
    }

    override fun resizable() {
        resizable = true
    }

    override fun undecorated() {
        undecorated = true
    }

    override fun decorated() {
        undecorated = false
    }
}