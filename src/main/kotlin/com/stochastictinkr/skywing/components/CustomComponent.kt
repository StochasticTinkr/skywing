package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.rendering.Painter
import com.stochastictinkr.skywing.rendering.use
import java.awt.Canvas
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D

class CustomComponent(
    preferredSize: Dimension? = null,
    minimumSize: Dimension? = preferredSize,
    maximumSize: Dimension? = null,
    var isActiveRendering: Boolean = false,
    var painter: Painter = {},
) : Canvas() {

    init {
        preferredSize?.let(::setPreferredSize)
        minimumSize?.let(::setMinimumSize)
        maximumSize?.let(::setMaximumSize)
    }

    fun painter(painter: Painter) {
        this.painter = painter
    }

    override fun paint(g: Graphics) {
        if (isActiveRendering) {
            return
        }
        if (isOpaque && background != null) {
            g.color = background
            g.fillRect(0, 0, width, height)
        }
        doPaint()
    }

    fun render() {
        if (!isVisible || !isActiveRendering) {
            return
        }
        doPaint()
    }

    private fun doPaint() {
        if (bufferStrategy == null) {
            createBufferStrategy(2)
        }
        do {
            (bufferStrategy.drawGraphics as Graphics2D).use { paintCanvas(it) }
            bufferStrategy.show()
        } while (bufferStrategy.contentsLost())
    }

    private fun paintCanvas(g: Graphics2D) {
        g.background = background
        painter(g, size)
    }
}
