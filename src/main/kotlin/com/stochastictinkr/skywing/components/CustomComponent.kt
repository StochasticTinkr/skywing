package com.stochastictinkr.skywing.components

import com.stochastictinkr.skywing.geom.dimension
import com.stochastictinkr.skywing.rendering.Painter
import com.stochastictinkr.skywing.rendering.use
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import javax.swing.JComponent
import javax.swing.border.AbstractBorder
import javax.swing.border.Border
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class CustomComponent(
    preferredSize: Dimension? = null,
    minimumSize: Dimension? = preferredSize,
    maximumSize: Dimension? = null,
    var painter: Painter = {},
) : JComponent() {

    init {
        preferredSize?.let(::setPreferredSize)
        minimumSize?.let(::setMinimumSize)
        maximumSize?.let(::setMaximumSize)
        isOpaque = true
    }

    fun painter(painter: Painter) {
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
        usingGraphics2d(g, width, height) {
            paintCanvas(it, width, height)
        }
    }

    private fun paintCanvas(g: Graphics2D, width: Int, height: Int) {
        g.background = background
        painter(g, dimension(width, height))
    }
}

private inline fun usingGraphics2d(g: Graphics, width: Int, height: Int, block: (Graphics2D) -> Unit) {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    if (g is Graphics2D) {
        (g.create() as Graphics2D).use(block)
    } else {
        BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB).also { img ->
            img.createGraphics().use(block)
            g.drawImage(img, 0, 0, null)
        }
    }
}
