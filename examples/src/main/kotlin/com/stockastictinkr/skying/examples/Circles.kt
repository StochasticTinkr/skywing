package com.stockastictinkr.skying.examples

import com.stochastictinkr.skywing.components.CustomComponent
import com.stochastictinkr.skywing.components.addComponent
import com.stochastictinkr.skywing.components.mainFrame
import com.stochastictinkr.skywing.geom.Radians
import com.stochastictinkr.skywing.geom.circle
import com.stochastictinkr.skywing.geom.component1
import com.stochastictinkr.skywing.geom.component2
import com.stochastictinkr.skywing.geom.div
import com.stochastictinkr.skywing.geom.plus
import com.stochastictinkr.skywing.geom.point
import com.stochastictinkr.skywing.geom.times
import com.stochastictinkr.skywing.initSkywing
import com.stochastictinkr.skywing.rendering.hints
import java.awt.Color
import java.awt.EventQueue
import javax.swing.JFrame
import javax.swing.Timer

fun main() {
    initSkywing("Circles")
    EventQueue.invokeLater {
        with(JFrame("Skywing Examples - Circles")) {
            mainFrame()
            var angle = Radians(0.0)
            addComponent(component = CustomComponent()) {
                painter { (width, height) ->
                    hints {
                        renderingQuality()
                        antialiasingOn()
                    }
                    background = Color.BLACK
                    clearRect(0, 0, width, height)
                    repeat(500) {
                        color = Color.getHSBColor(it / 150f, 1f, 1f)
                        fill(circle(point(width, height) / 2.0 + (angle * it.toDouble()).toPoint2D() * it, 5.0))
                    }
                }
                Timer(13) {
                    angle += Radians(Math.PI / 100000 * 10)
                    repaint()
                }.run {
                    isRepeats = true
                    start()
                }
            }
            isVisible = true
        }
    }
}