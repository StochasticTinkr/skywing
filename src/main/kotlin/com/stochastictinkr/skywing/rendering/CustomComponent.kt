@file:Suppress("unused")

package com.stochastictinkr.skywing.rendering

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import javax.swing.JComponent


class CustomComponent(
    var painter: PaintContext.() -> Unit = {},
) : JComponent() {
    init {
        isOpaque = true
    }
    fun painter(painter: PaintContext.() -> Unit) {
        this.painter = painter
    }

    override fun paintComponent(g: Graphics) {
        g.color = background
        g.fillRect(0, 0, width, height)
        withPaintContext(g)
    }

    private fun withPaintContext(g: Graphics) {
        if (g is Graphics2D) {
            paintAndDispose(g.create() as Graphics2D)
        } else {
            BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).also { img ->
                paintAndDispose(img.createGraphics())
                g.drawImage(img, 0, 0, null)
            }
        }
    }

    private fun paintAndDispose(g: Graphics2D) {
        try {
            g.background = background
            g.paint = foreground
            val paintableWidth = width - insets.right - insets.left
            val paintableHeight = height - insets.top - insets.bottom
            g.clipRect(insets.left, insets.top, paintableWidth, paintableHeight)
            g.translate(insets.left, insets.top)
            painter(PaintContext(g, paintableWidth, paintableHeight))
        } finally {
            g.dispose()
        }
    }
}

