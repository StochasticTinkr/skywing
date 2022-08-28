@file:Suppress("unused")

package com.stoachstictinkr.skywing.rendering

import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import javax.swing.JComponent


class CustomComponent(
    var painter: PaintContext.() -> Unit = {},
) : JComponent() {
    fun painter(painter: PaintContext.() -> Unit) {
        this.painter = painter
    }

    override fun paintComponent(g: Graphics) {
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
            painter(PaintContext(g, width, height))
        } finally {
            g.dispose()
        }
    }
}

