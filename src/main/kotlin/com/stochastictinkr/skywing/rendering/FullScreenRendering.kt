package com.stochastictinkr.skywing.rendering

import com.stochastictinkr.skywing.components.isFullScreen
import java.awt.BufferCapabilities
import java.awt.EventQueue
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.GraphicsConfiguration
import java.awt.GraphicsDevice
import java.awt.GraphicsEnvironment
import java.awt.ImageCapabilities
import java.awt.Window
import kotlin.concurrent.thread

fun fullScreenRendering(
    owner: Window? = null,
    graphicsEnvironment: GraphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment(),
    graphicsDevice: GraphicsDevice = owner?.graphicsConfiguration?.device ?: graphicsEnvironment.defaultScreenDevice,
    graphicsConfiguration: GraphicsConfiguration = owner?.graphicsConfiguration ?: graphicsDevice.defaultConfiguration,
    bufferCapabilities: BufferCapabilities = BufferCapabilities(
        ImageCapabilities(true),
        ImageCapabilities(true),
        BufferCapabilities.FlipContents.UNDEFINED
    ),
    updateState: () -> Boolean = { true },
    painter: Painter,
) {
    println("${painter}")
    EventQueue.invokeLater {
        object : Window(owner, graphicsConfiguration) {
            override fun paint(g: Graphics) {}
        }.apply {
            isFullScreen = true
            createBufferStrategy(2, bufferCapabilities)
            thread {
                try {
                    do {
                        do {
                            (bufferStrategy.drawGraphics as Graphics2D).use { it.painter(size) }
                            bufferStrategy.show()
                        } while (bufferStrategy.contentsLost())
                    } while (updateState())
                } finally {
                    isFullScreen = false
                }
            }
        }
    }
}