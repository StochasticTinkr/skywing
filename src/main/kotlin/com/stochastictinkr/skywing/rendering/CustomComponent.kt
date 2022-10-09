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
        var width = this.width
        var height = this.height
        if (isOpaque) {
            g.color = background
            g.fillRect(0, 0, width, height)
        }
        insets?.let { insets ->
            width = width - insets.right - insets.left
            height = height - insets.top - insets.bottom
            g.translate(insets.left, insets.right)
            g.setClip(0, 0, width, height)
        }
        withPaintContext(g, width, height)
    }

    private fun withPaintContext(g: Graphics, width: Int, height: Int) {
        if (g is Graphics2D) {
            paintAndDispose(g.create() as Graphics2D, width, height)
        } else {
            BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).also { img ->
                paintAndDispose(img.createGraphics(), width, height)
                g.drawImage(img, 0, 0, null)
            }
        }
    }

    private fun paintAndDispose(g: Graphics2D, width: Int, height: Int) {
        try {
            g.background = background
            g.paint = foreground
            painter(PaintContext(g, width, height))
        } finally {
            g.dispose()
        }
    }
}

